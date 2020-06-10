buildscript {
    BuildInfoFeature.applyPlugins(project)
    JavaMavenCentralPublishFeature.applyPlugins(project)
}

group = rootProject.group
version = rootProject.version

BuildInfoFeature.apply(project)
JavaMavenCentralPublishFeature.apply(project)
