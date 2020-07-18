import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar

object FileTreeFeature : CatalystPlugin {
    override fun applyPlugins(project: Project) {
    }

    override fun apply(project: Project) {
        project.tasks.register("fileTreeJar", Jar::class.java) {
            from(project.fileTree(project.projectDir))
            exclude {
                it.name == "build"
            }
        }
    }
}
