
// 配置仓库
repositories {
    google()
    mavenCentral() // 通常需要添加 Maven Central 仓库来获取 Kotlin 相关依赖
}

// 应用插件
plugins {
    groovy
    `java-gradle-plugin`
    kotlin("jvm") version "1.9.20" // 应用 Kotlin JVM 插件
}

// 配置依赖
dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation(kotlin("stdlib-jdk8", "1.9.20")) // 添加 Kotlin 标准库依赖，使用 JDK 8 版本
}
