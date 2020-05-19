import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class BuildInfo(val isRootSnapshotVersion: Boolean,
                     val rootGroup: String,
                     val rootName: String,
                     val rootVersionWithSnapshot: String,
                     val rootVersion: String,
                     val isModuleSnapshotVersion: Boolean,
                     val moduleGroup: String,
                     val moduleName: String,
                     val moduleVersionWithSnapshot: String,
                     val moduleVersion: String,
                     val environment: String,
                     val dataDirectory: String,
                     val commonDirectory: String,
                     val generatedDirectory: String,
                     val createdBy: String,
                     val localTimestamp: String,
                     val timeZoneName: String,
                     val buildNumber: String,
                     val javaVersion: String,
                     val operatingSystem: String,
                     val gradleVersion: String) {

    override fun toString(): String {
        val sb = StringBuilder()

        sb.append("-------------------------------------------------\n")
        sb.append("Tasks                  : gradle.startParameter.taskNames\n")
        sb.append("Environment            : envName\n")
        sb.append("Project group          : $moduleGroup\n")
        sb.append("Project name           : $moduleName\n")
        sb.append("Project version        : $moduleVersionWithSnapshot\n")
        sb.append("Project build          : $buildNumber\n")
        sb.append("Created By             : $createdBy\n")
        sb.append("Created On (local)     : $localTimestamp\n")
        sb.append("Time zone name         : $timeZoneName\n")
        sb.append("Java                   : $javaVersion\n")
        sb.append("OS                     : $operatingSystem\n")
        sb.append("Gradle                 : $gradleVersion\n")
        sb.append("-------------------------------------------------")
        sb.append("\n")

        return sb.toString()
    }
}

//ext.ENVIRONMENTS = ["local" : "0", "dev" : "1", "rd" : "2", "qc" : "3", "qb" : "4", "prod" : "5"]
//extra[buildInfo] = BuildInfo()

val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//project.ext.localTimestamp = LocalDateTime.now().format(dateTimeFormatter)
val zoneId = ZoneId.systemDefault().toString()

val rootGroup = rootProject.group.toString()
val rootName = rootProject.name
val rootVersionWithSnapshot = rootProject.version.toString()
val isRootSnapshotVersion = rootVersionWithSnapshot.toLowerCase().endsWith("-snapshot")
val rootVersion = if (isRootSnapshotVersion) rootVersionWithSnapshot.substring(0, rootVersionWithSnapshot.lastIndexOf("-")) else rootVersionWithSnapshot

val moduleGroup = group.toString()
val moduleName = name
val moduleVersionWithSnapshot = version.toString()
val isModuleSnapshotVersion = moduleVersionWithSnapshot.toLowerCase().endsWith("-snapshot")
val moduleVersion = if (isModuleSnapshotVersion) moduleVersionWithSnapshot.substring(0, moduleVersionWithSnapshot.lastIndexOf("-")) else moduleVersionWithSnapshot

val dataDirectory = "data"
val commonDirectory = "$dataDirectory/common"

val generatedDirectory = "$dataDirectory/generated"

val operatingSystem = System.getProperty("os.name")

val createdBy = if (operatingSystem.toLowerCase().contains("windows")) {
    System.getenv("USERNAME")
} else {
    System.getenv("USER")
}
//def envName = gradle.startParameter.taskNames.toString().toLowerCase().contains("all")? "all" : buildInfo.environment


val localTimestamp = dateTimeFormatter.format(java.time.LocalDateTime.now())
val timeZoneName = zoneId
val buildNumber = System.getProperty("buildNumber", "[" + localTimestamp + "]")
val javaVersion = System.getProperty("java.version")
val gradleVersion = gradle.gradleVersion
val environment = "local"

val buildInfo = BuildInfo(isRootSnapshotVersion, rootGroup, rootName, rootVersionWithSnapshot, rootVersion, isModuleSnapshotVersion, moduleGroup, moduleName, moduleVersionWithSnapshot, moduleVersion, environment, dataDirectory, commonDirectory, generatedDirectory, createdBy, localTimestamp, timeZoneName, buildNumber, javaVersion, operatingSystem, gradleVersion)
println(buildInfo)
