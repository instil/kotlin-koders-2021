package breakfast.model

import kotlinx.serialization.Serializable

enum class BeverageSize {
    Small,
    Medium,
    Large
}

enum class BeverageType {
    Expresso,
    Americano,
    CafeAuLait
}


@Serializable
class Beverage(val type: BeverageType, val size: BeverageSize) {
    fun drink() = println("Drinking a $size $type")
}
