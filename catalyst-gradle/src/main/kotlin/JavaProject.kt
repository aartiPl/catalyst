import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test

class JavaProject : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("java")

        project.tasks.create("signature") {
            println(BuildInfoResolver().resolve(project))
        }

        project.tasks.withType(JavaCompile::class.java).all {
            sourceCompatibility = JavaVersion.VERSION_1_8.toString()
            targetCompatibility = JavaVersion.VERSION_1_8.toString()
        }

        project.tasks.withType(Test::class.java).all {
            useJUnitPlatform()
        }

        project.tasks.getByPath("build").dependsOn("signature")
    }
}
