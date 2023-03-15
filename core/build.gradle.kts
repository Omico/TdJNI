plugins {
    application
    kotlin("jvm")
}

application {
    mainClass.set("Main")
}

val run by tasks.getting(JavaExec::class) {
    systemProperty("java.library.path", file("../jni/out/bin"))
}

val refreshHeader by tasks.registering {
    group = "jni"
    val jniSourceFile = file("src/main/java/me/omico/telegram/tdlib/Jni.java")
    inputs.file(jniSourceFile)
    outputs.file(file("../jni/td_jni.h"))
    doLast {
        val jniBuildDirectory = project.layout.buildDirectory.file("jni").get().asFile
        copy {
            from(jniSourceFile)
            into(jniBuildDirectory)
        }
        exec {
            workingDir(jniBuildDirectory)
            executable = "javac"
            args("-h", ".", jniSourceFile.name)
        }
        copy {
            from(File(jniBuildDirectory, "me_omico_telegram_tdlib_Jni.h"))
            into(file("../jni"))
            rename { "td_jni.h" }
        }
    }
}
