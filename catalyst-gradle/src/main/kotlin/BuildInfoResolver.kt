import org.gradle.api.Project
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object BuildInfoResolver {
    private const val snapshotSuffix = "-snapshot"
    private const val buildTimestampFormat = "yyyyMMdd'T'HHmmss'UTC'"
    private const val localTimestampFormat = "yyyy-MM-dd HH:mm:ss"

    fun resolve(project: Project): BuildInfo {
        val buildTimestamp = DateTimeFormatter.ofPattern(buildTimestampFormat).withZone(ZoneId.of("UTC")).format(
                Instant.now())
        val timeZoneName = ZoneId.systemDefault().toString()

        val rootGroup = project.rootProject.group.toString()
        val rootName = project.rootProject.name
        val rootVersionWithSnapshot = project.rootProject.version.toString()
        val (isRootSnapshotVersion, rootVersion) = decomposeVersion(rootVersionWithSnapshot)

        val moduleGroup = project.group.toString()
        val moduleName = project.name
        val moduleVersionWithSnapshot = project.version.toString()
        val (isModuleSnapshotVersion, moduleVersion) = decomposeVersion(moduleVersionWithSnapshot)

        val operatingSystem = System.getProperty("os.name")

        val createdBy = if (operatingSystem.toLowerCase().contains("windows")) {
            System.getenv("USERNAME")
        } else {
            System.getenv("USER")
        }

        val localTimestamp = DateTimeFormatter.ofPattern(localTimestampFormat).format(LocalDateTime.now())
        val buildNumber = System.getProperty("buildNumber", "[$buildTimestamp]")
        val javaVersion = System.getProperty("java.version")
        val gradleVersion = project.gradle.gradleVersion

        return BuildInfo(buildTimestamp,
                         project.gradle.startParameter.taskNames.toString(),
                         isRootSnapshotVersion,
                         rootGroup,
                         rootName,
                         rootVersionWithSnapshot,
                         rootVersion,
                         isModuleSnapshotVersion,
                         moduleGroup,
                         moduleName,
                         moduleVersionWithSnapshot,
                         moduleVersion,
                         buildNumber,
                         createdBy,
                         localTimestamp,
                         timeZoneName,
                         javaVersion,
                         operatingSystem,
                         gradleVersion)
    }

    private fun decomposeVersion(versionWithSnapshot: String): Pair<Boolean, String> {
        val isSnapshotVersion = versionWithSnapshot.toLowerCase().endsWith(snapshotSuffix)

        val version = if (isSnapshotVersion) {
            versionWithSnapshot.substring(0, versionWithSnapshot.length - snapshotSuffix.length)
        } else {
            versionWithSnapshot
        }

        return Pair(isSnapshotVersion, version)
    }
}
