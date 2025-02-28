package top.ntutn.buildsrc

import org.gradle.api.Plugin
import org.gradle.api.Project

class ManifestDemoPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.afterEvaluate {
            val mergeDebugResourcesTask = target.tasks.getByName("mergeDebugResources")
            val parseDebugTask = target.tasks.create("ParseDebugTask", ParseDebugTask::class.java)
            mergeDebugResourcesTask.finalizedBy(parseDebugTask)
        }
    }
}