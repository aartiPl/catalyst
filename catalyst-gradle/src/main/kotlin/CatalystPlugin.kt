import org.gradle.api.Project

interface CatalystPlugin {
    fun applyPlugins(project: Project)
    fun apply(project: Project)
}
