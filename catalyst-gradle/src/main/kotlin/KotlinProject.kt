import org.gradle.api.Project

object KotlinProject : CatalystPlugin {
    override fun applyPlugins(project: Project) {
        BuildInfoFeature.applyPlugins(project)
        KotlinFeature.applyPlugins(project)
        JavaTestingFeature.applyPlugins(project)
    }

    override fun apply(project: Project) {
        BuildInfoFeature.apply(project)
        KotlinFeature.apply(project)
        JavaTestingFeature.apply(project)
    }
}
