buildscript {
    FileTreeFeature.applyPlugins(project)
}

FileTreeFeature.apply(project)

project.artifacts {
    add("archives", project.tasks.named("fileTreeJar"))
}
