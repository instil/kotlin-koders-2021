package language.eg6

class Point(val x: Int, val y: Int) {
    operator fun plus(rhs: Point) = Point(x + rhs.x, y + rhs.y)
    operator fun minus(rhs: Point) = Point(x - rhs.x, y - rhs.y)

    override fun toString() = "$x $y"
}

fun main() {
    val point1 = Point(10, 20)
    val point2 = Point(30, 40)

    val point3 = point1 + point2
    val point4 = point1 - point2

    println(point3)
    println(point4)
}
