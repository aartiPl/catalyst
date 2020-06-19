buildscript {
    MavenCentralPublishFeature.applyPlugins(project)
}

group = rootProject.group
version = rootProject.version

MavenCentralPublishFeature.apply(project)
