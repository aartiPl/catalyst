import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.plugins.signing.SigningExtension

class BuildInfoFeature : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.create("buildInfo") {
            println(BuildInfoResolver().resolve(project))
        }

        project.tasks.getByPath("build").dependsOn("buildInfo")
    }
}
