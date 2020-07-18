buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("org.gradle.kotlin:plugins:1.3.+")
    }
}

val isBuildSrc = (project.name == "buildSrc")

if (isBuildSrc) {
    apply(from = "buildsrc_build.gradle.kts")
} else {
    apply(from = "standard_build.gradle.kts")
}
