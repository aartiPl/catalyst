import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer

class JavaExampleSourceFeature : Plugin<Project> {
    override fun apply(project: Project) {
        val sourceSets = project.properties["sourceSets"] as SourceSetContainer

        sourceSets.register("example").configure {
            java.srcDir("src/example/java")
            compileClasspath = sourceSets.named("main").get().compileClasspath
            runtimeClasspath = sourceSets.named("main").get().compileClasspath
        }

        project.configurations.named("exampleImplementation").get().extendsFrom(project.configurations.named("implementation").get())
    }
}
