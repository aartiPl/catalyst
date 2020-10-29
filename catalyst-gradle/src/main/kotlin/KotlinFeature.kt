import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.ArtifactRepository
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.ScriptHandlerScope.*
import java.net.URI

object KotlinFeature : CatalystPlugin {
    const val kotlinVersion = "1.4.10"

    override fun applyPlugins(project: Project) {
        project.repositories.maven {
            url = URI.create("https://plugins.gradle.org/m2/")
        }

        project.buildscript.dependencies.add("classpath", "org.gradle.kotlin:plugins:1.4.10")

        JarFeature.applyPlugins(project)
    }

    override fun apply(project: Project) {
        //project.buildscript.dependencies.add("classpath", "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        project.plugins.apply("org.jetbrains.kotlin.jvm")

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

        //project.dependencies.add("implementation", kotlin("stdlib-jdk8"))
    }
}
