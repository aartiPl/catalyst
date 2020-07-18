buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("org.gradle.kotlin:plugins:1.3.6")
    }
}

val isBuildSrc = (project.name == "buildSrc")

if (isBuildSrc) {
    apply(from = "buildsrc_build.gradle.kts")
} else {
    apply(from = "standard_build.gradle.kts")
}

afterEvaluate {
    //kotlin-dsl automatically adds plugin publish tasks to publications
    //the only way to disable it is as below:
    project.tasks.forEach { t ->
        if (t.project.name == "catalyst-gradle" && t.name.contains("plugin", ignoreCase = true)) {
            println("Disabling task: ${t.project.name}:${t.name}")
            t.enabled = false
        }
    }
}
