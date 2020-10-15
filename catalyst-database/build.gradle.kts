plugins {
    kotlin("jvm") version "1.4.10"
}

//buildscript {
//    KotlinProject.applyPlugins(project)
//}

KotlinProject.apply(project)
//apply(plugin = "org.jetbrains.kotlin.jvm")
//apply(plugin = "org.gradle.kotlin.kotlin-dsl")

dependencies {
    implementation("log4j:log4j:1.2.17")
    implementation("org.apache.commons:commons-csv:1.2")
    implementation("org.apache.commons:commons-compress:1.11")
    implementation("commons-io:commons-io:2.4")
    implementation("org.codehaus.groovy:groovy-all:2.4.5")
    implementation("com.google.code.gson:gson:2.7")
    implementation("com.owlike:genson:1.4")
    implementation("com.google.guava:guava:29.0-jre")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.apache.commons:commons-lang3:3.10")

    implementation("io.github.microutils:kotlin-logging:1.12.0")
    implementation("com.zaxxer:HikariCP:3.4.5")
}
