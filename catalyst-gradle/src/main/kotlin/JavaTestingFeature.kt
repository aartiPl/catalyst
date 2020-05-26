import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

class JavaTestingFeature : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.withType(Test::class.java).all {
            useJUnitPlatform()
            reports.html.isEnabled = true
        }

        project.dependencies.add("testImplementation", "org.assertj:assertj-core:3.16.1")
        project.dependencies.add("testImplementation", "org.junit.jupiter:junit-jupiter:5.6.2")
    }
}
