import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.extra

object JarFeature : CatalystPlugin {
    override fun applyPlugins(project: Project) {
        //project.plugins.apply("java")
    }

    override fun apply(project: Project) {
        project.tasks.withType(Jar::class.java).all {
            val buildInfo = project.extra["buildInfo"] as BuildInfo

            manifest {
                attributes(mapOf("Environment" to "local",
                                 "Implementation-Group" to buildInfo.moduleGroup,
                                 "Implementation-Name" to buildInfo.moduleName,
                                 "Implementation-Version" to buildInfo.moduleVersionWithSnapshot,
                                 "Implementation-BuildId" to buildInfo.buildId,
                                 "Created-By" to buildInfo.createdBy,
                                 "Created-On" to "${buildInfo.createdOn} [${buildInfo.creationZone}]"))
            }
        }
    }
}
