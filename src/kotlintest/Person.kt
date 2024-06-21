package kotlintest

import java.io.File

class Person {
    fun test() {
        println("test kotlintest of person")
        val projectPath = System.getProperty("user.dir")
        val file = File("")
        println("projectPath in kotlin=======" + projectPath + " file absolutepath=" + file.absolutePath)
    }
}