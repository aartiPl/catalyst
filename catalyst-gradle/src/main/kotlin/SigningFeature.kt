import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.plugins.signing.SigningExtension

class SigningFeature : Plugin<Project> {
    override fun apply(project: Project) {
        if (project.hasProperty("signing.keyId")) {

            project.plugins.apply("signing")

            project.extensions.configure<SigningExtension>("signing") {
                sign(project.configurations.named("archives").get())
            }
        }
    }
}
