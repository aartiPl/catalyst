buildscript {
    project.extra["isBuildSrc"] = project.name == "buildSrc"

    project.plugins.apply("signing")
    project.plugins.apply("maven-publish")

}

plugins {
    `kotlin-dsl`
}

group = rootProject.group
version = rootProject.version

if (!(project.extra["isBuildSrc"] as Boolean)) {
    signing {
        sign(project.configurations.named("archives").get())
    }

    project.tasks.register("fileTreeJar", Jar::class.java) {
        from(fileTree(project.projectDir))
        exclude {
            it.name == "build"
        }
    }

    val mavenPublishUser = project.properties["ossrhUsername"] as String
    val mavenPublishPassword = project.properties["ossrhPassword"] as String
    val snapshotSuffix = "-snapshot"
    val isModuleSnapshotVersion = project.version.toString().toLowerCase().endsWith(snapshotSuffix)

    publishing {
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

        publications {
            create<MavenPublication>("fileTree") {
                artifact(project.tasks.named("fileTreeJar").get())

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
