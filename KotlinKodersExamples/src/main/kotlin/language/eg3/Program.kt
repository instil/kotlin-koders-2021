package language.eg3

fun main() {
    demo(123)
    demo(45.6)
}

fun demo(input: Int) {
    fun printMsg(text: String) = println(text)
    printMsg("Hello Kotlin Koders!")
}

fun demo(input: Double) {
    fun printMsg(text: String) = println(text)
    printMsg("Bonjour Kotlin Koders!")
}
