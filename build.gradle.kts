buildscript {
    copy {
        from("${project.rootDir}/catalyst-gradle")
        into("${project.rootDir}/buildSrc")
    }
}

group = "net.igsoft.catalyst"
version = "0.1.0-SNAPSHOT"
