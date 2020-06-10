import org.gradle.api.Project
import org.gradle.plugins.signing.SigningExtension

object SigningFeature : CatalystPlugin {
    override fun applyPlugins(project: Project) {
        if (project.hasProperty("signing.keyId")) {
            project.plugins.apply("signing")
        }
    }

    override fun apply(project: Project) {
        if (project.hasProperty("signing.keyId")) {
            project.extensions.configure<SigningExtension>("signing") {
                sign(project.configurations.named("archives").get())
            }
        }
    }
}
