buildscript {
    copy {
        from("${rootDir}/catalyst-gradle")
        into("${rootDir}/buildSrc")
    }
}

group = "net.igsoft.catalyst"
version = "0.1.0-SNAPSHOT"

subprojects {
    buildscript {
        MavenCentralPublishFeature.applyPlugins(project)
    }

    project.group = rootProject.group
    project.version = rootProject.version

    MavenCentralPublishFeature.apply(project)

    configure<PublishingExtension> {
        publications.create<MavenPublication>("mavenPublication") {
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


    afterEvaluate {
        configure<PublishingExtension> {
            publications.named("mavenPublication", MavenPublication::class.java).configure {
                setArtifacts(listOf<Artifact>())

                val map = mutableMapOf<String, PublishArtifact>()
                project.configurations.named("archives").get().artifacts.forEach {
                    map[it.toString()] = it
                }

                println("Archives to be published:")
                map.forEach {
                    println(it.key)
                }

                map.forEach {
                    artifact(it.value)
                }
            }
        }
    }
}
