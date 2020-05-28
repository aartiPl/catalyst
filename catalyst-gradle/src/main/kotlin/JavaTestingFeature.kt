import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

object JavaTestingFeature : CatalystPlugin {
    override fun applyPlugins(project: Project) {
    }

    override fun apply(project: Project) {
        project.tasks.withType(Test::class.java).all {
            useJUnitPlatform()
            reports.html.isEnabled = true
        }

        project.dependencies.add("testImplementation", "org.junit.jupiter:junit-jupiter:5.6.2")
        project.dependencies.add("testImplementation", "org.assertj:assertj-core:3.16.1")

        //project.dependencies.platform()

//        project.dependencies.constraints.add("testImplementation", "org.assertj:assertj-core") {
//            version { strictly("3.16.1") }
//        }
    }
}
