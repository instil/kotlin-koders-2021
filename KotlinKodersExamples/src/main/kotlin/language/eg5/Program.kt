package language.eg5

class Person(val name: String)

fun Person.sayHello() = println("Hello from $name")

fun String.times(num: Int) = (1..num).joinToString { "$this" }

fun main() {
    val person = Person("Jane")
    person.sayHello()

    println("Dave".times(3))
}
