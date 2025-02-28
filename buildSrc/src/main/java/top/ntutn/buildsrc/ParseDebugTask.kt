package top.ntutn.buildsrc

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ParseDebugTask: DefaultTask() {
    @TaskAction
    fun doAction() {
        println("Hello from kt plugin")
    }
}
