data class BuildInfoPropertyValue(val value: Any, val description: String)

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
