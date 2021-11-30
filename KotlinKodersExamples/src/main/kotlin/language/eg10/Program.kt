package language.eg10

enum class Colour {
    Red,
    Green,
    Blue
}

fun main() {
    Colour.values().forEach(::println)
}
