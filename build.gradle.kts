buildscript {
    copy {
        from("${rootDir}/catalyst-gradle")
        into("${rootDir}/buildSrc")
    }
}

group = "net.igsoft.catalyst"
version = "0.1.0-SNAPSHOT"

//allprojects {
//
//}
