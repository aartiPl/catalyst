import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.extra

object JavaFeature : CatalystPlugin {
    override fun applyPlugins(project: Project) {
        project.plugins.apply("java")
        JarFeature.applyPlugins(project)
    }

    override fun apply(project: Project) {
        project.tasks.withType(JavaCompile::class.java).all {
            sourceCompatibility = JavaVersion.VERSION_1_8.toString()
            targetCompatibility = JavaVersion.VERSION_1_8.toString()
        }

        project.tasks.register("javadocJar", Jar::class.java) {
            archiveClassifier.set("javadoc")
            from(project.tasks.named("javadoc"))
        }

        val sourceSets = project.properties["sourceSets"] as SourceSetContainer

        project.tasks.register("sourcesJar", Jar::class.java) {
            archiveClassifier.set("sources")
            from(sourceSets.named("main").get().allSource)
        }

        JarFeature.apply(project)

        project.artifacts {
            add("archives", project.tasks.named("javadocJar"))
            add("archives", project.tasks.named("sourcesJar"))
        }
    }
}
