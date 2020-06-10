import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*

object JavaMavenCentralPublishFeature : CatalystPlugin {
    override fun applyPlugins(project: Project) {
        SigningFeature.applyPlugins(project)
        project.plugins.apply("maven-publish")
    }

    override fun apply(project: Project) {
        SigningFeature.apply(project)

        val mavenPublishUser = project.properties["ossrhUsername"] as String
        val mavenPublishPassword = project.properties["ossrhPassword"] as String
        val buildInfo = project.extra["buildInfo"] as BuildInfo

        project.extensions.configure<PublishingExtension>("publishing") {
            repositories {
                maven {
                    name = "MavenCentral"
                    val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
                    val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots"
                    url = project.uri(if (buildInfo.isModuleSnapshotVersion) snapshotsRepoUrl else releasesRepoUrl)
                    credentials {
                        username = mavenPublishUser
                        password = mavenPublishPassword
                    }
                }
            }

            publications {
                create<MavenPublication>("mavenJava") {
                    project.configurations.named("archives").get().artifacts.forEach {
                        println(it)
                        artifact(it)
                    }
//                    from(project.components["java"])
//                    artifact(project.tasks.named("sourcesJar"))
//                    artifact(project.tasks.named("javadocJar"))

                    pom {
                        name.set("Catalyst libraries")
                        description.set("Set of libraries for common programming tasks.")
                        url.set("https://github.com/aartiPl/catalyst")

                        licenses {
                            license {
                                name.set("MIT License")
                                url.set("https://opensource.org/licenses/MIT")
                            }
                        }

                        developers {
                            developer {
                                id.set("aartiPl")
                                name.set("Marcin Kuszczak")
                                email.set("marcin.kuszczak.apps@gmail.com")
                            }
                        }

                        scm {
                            connection.set("scm:git:git://https://github.com/aartiPl/catalyst.git")
                            developerConnection.set("scm:git:ssh:https://github.com/aartiPl/catalyst.git")
                            url.set("https://github.com/aartiPl/catalyst/tree/master")
                        }
                    }
                }
            }
        }
    }
}
