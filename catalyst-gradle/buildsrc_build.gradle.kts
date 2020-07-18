// kotlin-dsl plugin automatically adds plugin publish tasks to publications.
// Because of that kotlin plugin must be applied only for buildSrc build, not for standard build.
// Otherwise it creates mavenPublication which conflicts with catalyst-gradle publication.
apply(plugin = "org.gradle.kotlin.kotlin-dsl")
