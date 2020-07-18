import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension

object MavenCentralPublishFeature : CatalystPlugin {
    override fun applyPlugins(project: Project) {
        SigningFeature.applyPlugins(project)
        project.plugins.apply("maven-publish")
    }

    override fun apply(project: Project) {
        SigningFeature.apply(project)

        val mavenPublishUser = project.properties["ossrhUsername"] as String
        val mavenPublishPassword = project.properties["ossrhPassword"] as String
        val snapshotSuffix = "-snapshot"
        val isModuleSnapshotVersion = project.version.toString().toLowerCase().endsWith(snapshotSuffix)

        project.extensions.configure<PublishingExtension>("publishing") {
            repositories {
                maven {
                    name = "MavenCentral"
                    val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
                    val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots"
                    url = project.uri(if (isModuleSnapshotVersion) snapshotsRepoUrl else releasesRepoUrl)
                    credentials {
                        username = mavenPublishUser
                        password = mavenPublishPassword
                    }
                }
            }
        }
    }
}
