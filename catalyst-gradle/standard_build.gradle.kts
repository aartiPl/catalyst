buildscript {
    FileTreeFeature.applyPlugins(project)
}

FileTreeFeature.apply(project)

project.artifacts {
    add("archives", project.tasks.named("fileTreeJar"))
}

//Comment below line before publishing artifact.
//For editing it should be enabled.
apply(plugin = "org.gradle.kotlin.kotlin-dsl")
