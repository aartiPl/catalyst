import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.StandardCharsets
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

internal object Enc {
    private fun base64Encode(bytes: ByteArray): String {
        return Base64.getEncoder().encodeToString(bytes)
    }

    private fun base64Decode(property: String): ByteArray {
        return Base64.getDecoder().decode(property)
    }

    private fun createSecretKey(password: String, salt: ByteArray, iterationCount: Int, keyLength: Int): SecretKeySpec {
        val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
        val keySpec = PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength)
        val keyTmp = keyFactory.generateSecret(keySpec)
        return SecretKeySpec(keyTmp.encoded, "AES")
    }

    fun encrypt(property: String): String {
        val p = (Math.random() * (mingledChars.length - 20)).toInt()
        val key = createSecretKey(mingledChars.substring(p, p + 20), "12345678".toByteArray(), 40000, 128)
        val pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        pbeCipher.init(Cipher.ENCRYPT_MODE, key)
        val parameters = pbeCipher.parameters
        val ivParameterSpec = parameters.getParameterSpec(IvParameterSpec::class.java)
        val cryptoText = pbeCipher.doFinal(property.toByteArray(charset("UTF-8")))
        val iv = ivParameterSpec.iv
        return base64Encode(p.toString().toByteArray()) + ":" + base64Encode(iv) + ":" + base64Encode(cryptoText)
    }

    fun decrypt(string: String): String {
        return try {
            val p = String(base64Decode(string.split(":").toTypedArray()[0])).toInt()
            val iv = string.split(":").toTypedArray()[1]
            val property = string.split(":").toTypedArray()[2]
            val key = createSecretKey(mingledChars.substring(p, p + 20), "12345678".toByteArray(), 40000, 128)
            val pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            pbeCipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(base64Decode(iv)))
            String(pbeCipher.doFinal(base64Decode(property)), StandardCharsets.UTF_8)
        } catch (e: Exception) {
            println(e)
            ""
        }
    }

    private const val mingledChars = "asfdbsdaodio83u4oqiaoueq92843uiqerikz;slmv.,zkjf;aslkjfwi4yrpoaijwdoqwy3rupoqi3h4tliwuep'oasjdflsehr;tij" + "sadfjas;ldfjas;ldfjas;ldkfja;slkfdjA;SKCD'FG;W]W4efrglse;'okfa;slkdf';aSLKFD;ESGRJ;SLKJGR';SDKGF';AEK;lajhsdcvlkzud ;qlei3rhalskduhf ;LKWAJEBRKXU"
}

class PropertyHandler(private val path: Path) {
    private val properties = Properties()

    init {
        FileInputStream(path.toFile()).use {
            properties.load(it)
        }
    }

    fun read(property: String): String {
        return properties.getProperty(property)
    }

    fun write(property: String, value: String) {
        properties.setProperty(property, value)

        FileOutputStream(path.toFile()).use {
            properties.store(it, "# Gradle properties")
        }
    }
}

class UpdatePasswordTask @Inject constructor(private val initPath: String) : DefaultTask() {
    @TaskAction
    fun updatePassword() {
        println("Please provide password: ")
        val passwd: String = readLine()!!
        println("Updating password...")
        val encrypted = Enc.encrypt(passwd)
        println(encrypted)

        val ph = PropertyHandler(Paths.get(initPath.toString(), "gradle.properties")!!)
        ph.write("mavenPassword", encrypted)
    }
}

fun extractFolder(zipFile: String, extractFolder: String) {
    val bufferSize = 2048
    val file = File(zipFile)
    val zip = ZipFile(file)
    File(extractFolder).mkdir()
    val zipFileEntries = zip.entries()

    while (zipFileEntries.hasMoreElements()) {
        val entry = zipFileEntries.nextElement() as ZipEntry
        val currentEntry = entry.name
        val destFile = File(extractFolder, currentEntry)
        val destinationParent = destFile.parentFile
        destinationParent.mkdirs()

        if (!entry.isDirectory) {
            val inputStream = BufferedInputStream(zip.getInputStream(entry))
            var currentByte = 0
            val data = ByteArray(bufferSize)
            val fos = FileOutputStream(destFile)
            val dest = BufferedOutputStream(fos, bufferSize)

            while (inputStream.read(data, 0, bufferSize).also { currentByte = it } != -1) {
                dest.write(data, 0, currentByte)
            }
            dest.flush()
            dest.close()
            inputStream.close()
        }
        if (currentEntry.endsWith(".zip")) {
            extractFolder(zipFile, destFile.absolutePath)
        }
    }
}

class CustomEventLogger : BuildAdapter(), TaskExecutionListener {
    override fun beforeExecute(task: Task) {}
    override fun afterExecute(task: Task, state: TaskState) {}
    override fun buildFinished(result: BuildResult) {}
}

// For some reason below doesn't work... Can not reference also initscript property...
//initscript {}

buildscript {

}

/*
useLogger(CustomEventLogger())

rootProject.configure<Project> {
    val scriptPath = ((File)(initscript.sourceFile)).toPath()
    val pathCount = scriptPath.getNameCount()
    val initPath = Paths.get (scriptPath.getRoot().toString(), scriptPath.subpath(0, pathCount-1).toString()).toString()
    ext.initPath = initPath

    task updatePassword (type: UpdatePasswordTask, constructorArgs: [initPath])

    task showRepos {
        doLast {
            println "All repos:"
            println repositories . collect { it.name }
        }
    }
}

allprojects {
    buildscript {
        ext.mavenPlainPassword = Enc.decrypt(mavenPassword)

        repositories {
            mavenLocal()
            mavenCentral()
        }

        ext.extract = {
            String parentArtifact, String commonDirPath = "buildSrc" ->
            dependencies.classpath parentArtifact

                    afterEvaluate { project ->
                        String commonDir = "${rootProject.projectDir}/$commonDirPath"

                        ResolvedArtifact resolvedArtifact = configurations . classpath . resolvedConfiguration . resolvedArtifacts . find {
                            it.moduleVersion.toString() == parentArtifact
                        }

                        if (resolvedArtifact == null) {
                            throw new IllegalStateException ("Can not find artifact: $parentArtifact")
                        }

                        println "Resolved artifact: $resolvedArtifact\nCommon dir: $commonDir"

                        extractFolder(resolvedArtifact.file.toString(), commonDir)

                        //NOTE: below doesn't work
//                copy() {
//                  from zipTree(resolvedArtifact.file)
//                 into commonDir
//                }
                    }
        }
    }

    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
    }
}
*/
