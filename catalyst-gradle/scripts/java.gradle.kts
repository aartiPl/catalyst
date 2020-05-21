apply(plugin = "java")
//apply plugin: 'maven'

//sourceCompatibility = 1.8
//targetCompatibility = 1.8

//apply from: "$topazCommonsDir/gradle/scripts/proxy.gradle"
//apply from: "$topazCommonsDir/gradle/scripts/repositories.gradle"
//apply from: "$topazCommonsDir/gradle/scripts/wrapper.gradle"
//apply from: "$topazCommonsDir/gradle/scripts/ide.gradle"
//apply from: "$topazCommonsDir/gradle/scripts/sourcesjavadoc.gradle"
//apply from: "$topazCommonsDir/gradle/scripts/publish.gradle"
//apply from: "$topazCommonsDir/gradle/scripts/dependencies.gradle"
//apply from: "$topazCommonsDir/gradle/scripts/codeanalysis.gradle"

apply(from = "${rootProject.extra["commonDir"]}/scripts/build-info.gradle.kts")      // structure
//apply from: "$topazCommonsDir/gradle/scripts/environment.gradle"    // setting default
//apply from: "$topazCommonsDir/gradle/scripts/envSetting.gradle"     // modify
//apply from: "$topazCommonsDir/gradle/scripts/distribution.gradle"   // modify
//apply from: "$topazCommonsDir/gradle/scripts/resources.gradle"      // using
//apply from: "$topazCommonsDir/gradle/scripts/jar.gradle"            // using
//apply from: "$topazCommonsDir/gradle/scripts/printing.gradle"       // printing
