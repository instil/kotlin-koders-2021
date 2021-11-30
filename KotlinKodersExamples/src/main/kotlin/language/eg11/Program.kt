package language.eg11

data class Person(val name: String, var age: Int)

fun demo(person: Person, action: Person.() -> Unit) = person.apply(action)

fun main() {
    val person = Person("Jane", 25)
    demo(person) {
        age += 10
        println(this)
    }
}
