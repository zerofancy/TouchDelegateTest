package top.ntutn.buildsrc

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class ParseDebugTask: DefaultTask() {
    @TaskAction
    fun doAction() {
        println("Hello from kt plugin")

        val file = project.layout.buildDirectory.file("intermediates/merged_manifest/debug/processDebugMainManifest/AndroidManifest.xml").get()
        val content = file.asFile.readText()
        file.asFile.writeText(content.replace("@string/app_name", "测试插件修改"))
    }
}
