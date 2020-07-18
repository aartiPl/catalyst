buildscript {
    FileTreeFeature.applyPlugins(project)
}

FileTreeFeature.apply(project)

configure<PublishingExtension> {
    publications.named("mavenPublication", MavenPublication::class.java).configure {
        setArtifacts(listOf<Artifact>())

    }
}

project.artifacts {
    add("archives", project.tasks.named("fileTreeJar"))
}
