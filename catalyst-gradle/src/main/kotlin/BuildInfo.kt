data class BuildInfoPropertyValue(val value: Any, val description: String)

data class BuildInfo(val buildTimestamp: String,
                     val currentTasks: String,
                     val isRootSnapshotVersion: Boolean,
                     val rootGroup: String,
                     val rootName: String,
                     val rootVersionWithSnapshot: String,
                     val rootVersion: String,
                     val isModuleSnapshotVersion: Boolean,
                     val moduleGroup: String,
                     val moduleName: String,
                     val moduleVersionWithSnapshot: String,
                     val moduleVersion: String,
                     val buildId: String,
                     val createdBy: String,
                     val createdOn: String,
                     val creationZone: String,
                     val javaVersion: String,
                     val operatingSystem: String,
                     val gradleVersion: String) {

    override fun toString(): String {
        val sb = StringBuilder()

        sb.append("----------------------------------------------------------------\n")
        sb.append("Current Tasks      : $currentTasks\n")
        sb.append("OS                 : $operatingSystem\n")
        sb.append("Module Group       : $moduleGroup\n")
        sb.append("Module Name        : $moduleName\n")
        sb.append("Module Version     : $moduleVersionWithSnapshot\n")
        sb.append("Module Build Id    : $buildId\n")
        sb.append("Created By         : $createdBy\n")
        sb.append("Created On         : $createdOn ($creationZone)\n")
        sb.append("Java               : $javaVersion\n")
        sb.append("Gradle             : $gradleVersion\n")
        sb.append("----------------------------------------------------------------")
        sb.append("\n")

        return sb.toString()
    }
}
