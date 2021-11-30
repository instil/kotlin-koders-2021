package language.eg9

class Employee(val name: String, val dept: String) {
    companion object {
        fun buildForHR(name: String) = Employee(name, "HR")
        fun buildForIT(name: String) = Employee(name, "IT")
    }

    override fun toString() = "$name working in $dept"
}

fun main() {
    val emp1 = Employee.buildForHR("Dave")
    val emp2 = Employee.buildForIT("Jane")

    println(emp1)
    println(emp2)
}
