package language.eg7

data class Person(val name: String, val age: Int)

fun main() {
    val person = Person("Lucy", 36)
    val (x, y) = person

    println(x)
    println(y)
}
