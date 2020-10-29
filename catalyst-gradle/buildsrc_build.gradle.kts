// kotlin-dsl plugin automatically adds plugin publish tasks to mavenPublication.
// Because of that it is necessary to publish artifacts using catalystPublication, not mavenPublication.
// It's also not possible to just use "publish" as it will try to publis also mavenPublication,
// which have the same artifact catalyst-gradle.
apply(plugin = "org.gradle.kotlin.kotlin-dsl")
