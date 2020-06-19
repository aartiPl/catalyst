buildscript {
    JavaProject.applyPlugins(project)
    MavenCentralPublishFeature.applyPlugins(project)
}

group = rootProject.group
version = rootProject.version

JavaProject.apply(project)
MavenCentralPublishFeature.apply(project)

project.extensions.configure<PublishingExtension>("publishing") {
    publications.create<MavenPublication>("mavenJava") {

        println("Archives to be published:")
        project.configurations.named("archives").get().artifacts.forEach {
            println(it)
        }

        project.configurations.named("archives").get().artifacts.forEach {
            artifact(it)
        }

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


dependencies {
    implementation("log4j:log4j:1.2.17")
    implementation("org.apache.commons:commons-csv:1.2")
    implementation("org.apache.commons:commons-compress:1.11")
    implementation("commons-io:commons-io:2.4")
    implementation("org.codehaus.groovy:groovy-all:2.4.5")
    implementation("com.google.code.gson:gson:2.7")
    implementation("com.owlike:genson:1.4")
    implementation("com.google.guava:guava:29.0-jre")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.apache.commons:commons-lang3:3.10")

    //testImplementation("org.assertj:assertj-core")
}
