import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

object BuildInfoFeature : CatalystPlugin {
    const val buildInfoString = "buildInfo"

    override fun applyPlugins(project: Project) {
    }

    override fun apply(project: Project) {
        project.tasks.create(buildInfoString) {
            project.extra[buildInfoString] = BuildInfoResolver.resolve(project)

            println(project.extra[buildInfoString])
        }

        project.tasks.getByPath("build").dependsOn(buildInfoString)
    }
}
