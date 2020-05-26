import org.gradle.api.Project
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class BuildInfoResolver {
    fun resolve(project: Project): BuildInfo {
        //ext.ENVIRONMENTS = ["local" : "0", "dev" : "1", "rd" : "2", "qc" : "3", "qb" : "4", "prod" : "5"]
//extra[buildInfo] = BuildInfo()

        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//project.ext.localTimestamp = LocalDateTime.now().format(dateTimeFormatter)
        val zoneId = ZoneId.systemDefault().toString()

        val rootGroup = project.rootProject.group.toString()
        val rootName = project.rootProject.name
        val rootVersionWithSnapshot = project.rootProject.version.toString()
        val isRootSnapshotVersion = rootVersionWithSnapshot.toLowerCase().endsWith("-snapshot")
        val rootVersion = if (isRootSnapshotVersion) rootVersionWithSnapshot.substring(0, rootVersionWithSnapshot.lastIndexOf("-")) else rootVersionWithSnapshot

        val moduleGroup = project.group.toString()
        val moduleName = project.name
        val moduleVersionWithSnapshot = project.version.toString()
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
        val gradleVersion = project.gradle.gradleVersion
        val environment = "local"

        return BuildInfo(isRootSnapshotVersion, rootGroup, rootName, rootVersionWithSnapshot, rootVersion, isModuleSnapshotVersion, moduleGroup, moduleName, moduleVersionWithSnapshot, moduleVersion, environment, dataDirectory, commonDirectory, generatedDirectory, createdBy, localTimestamp, timeZoneName, buildNumber, javaVersion, operatingSystem, gradleVersion)
    }
}
