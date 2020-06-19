buildscript {
    MavenCentralPublishFeature.applyPlugins(project)
}

group = rootProject.group
version = rootProject.version

project.tasks.register("fileTreeJar", Jar::class.java) {
    from(fileTree(project.projectDir))
    exclude {
        it.name == "build"
    }
}

project.artifacts {
    add("archives", project.tasks.named("fileTreeJar"))
}

MavenCentralPublishFeature.apply(project)

project.extensions.configure(PublishingExtension::class.java) {

    publications.create<MavenPublication>("mavenFileTree") {
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
