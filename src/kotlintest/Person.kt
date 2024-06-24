package kotlintest

import java.io.File

class Person {
    fun test() {
        val projectPath = System.getProperty("user.dir")
        val file = File("")
        println("test kotlintest of person projectPath in kotlin=======" + projectPath + " file absolutepath=" + file.absolutePath)
    }
}