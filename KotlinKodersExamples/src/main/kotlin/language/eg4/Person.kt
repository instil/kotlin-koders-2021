package language.eg4

class Person(val name: String, var age: Int) {
    private val fullName: String

    init {
        fullName = "$name Jones"
    }

    override fun toString() = "$fullName aged $age"
}

fun main() {
    val person = Person("Bridget", 30)
    println(person)
}
