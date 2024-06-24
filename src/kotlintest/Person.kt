package kotlintest

import java.io.File

class Person {
    var age = 0
    var height = 0
    var dead = false

    constructor(ageP: Int = 1, heightP: Int = 1, deadP: Boolean = false) {
        age = ageP
        height = heightP
        dead = deadP
    }

    override fun toString(): String {
        return "age=${age} height=${height} dead=${dead}"
    }

    fun test(vararg arg: String?) {
        val p = Person(deadP = true, ageP = 3)
        val projectPath = System.getProperty("user.dir")
        val file = File("")
        println("test kotlintest of person projectPath in kotlin=======" + projectPath + " file absolutepath=" + file.absolutePath + " p=${p}")
    }
}