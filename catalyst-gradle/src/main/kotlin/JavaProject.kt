import org.gradle.api.Project

object JavaProject : CatalystPlugin {
    override fun applyPlugins(project: Project) {
        BuildInfoFeature.applyPlugins(project)
        JavaFeature.applyPlugins(project)
        JavaTestingFeature.applyPlugins(project)
    }

    override fun apply(project: Project) {
        BuildInfoFeature.apply(project)
        JavaFeature.apply(project)
        JavaTestingFeature.apply(project)
    }
}
