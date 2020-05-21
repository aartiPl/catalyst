buildscript {
    project.extra["commonDir"] = "${project.rootDir}/catalyst-gradle"

    project.extra.properties.forEach {  p-> println(p) }
}

group = "com.bbh.ta.topaz"
version = "0.6.0-SNAPSHOT"

//val bi = BuildInfo()
