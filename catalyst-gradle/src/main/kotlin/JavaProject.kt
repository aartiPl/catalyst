import org.gradle.api.Plugin
import org.gradle.api.Project

class JavaProject : Plugin<Project> {
    override fun apply(project: Project) {
        JavaFeature().apply(project)
        JavaTestingFeature().apply(project)
        BuildInfoFeature().apply(project)
    }
}
