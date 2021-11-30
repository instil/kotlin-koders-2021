package breakfast.model

import kotlinx.serialization.Serializable

@Serializable
class Food(
    private val name: String,
    private val servingSize: Int,
    private val withChocolate: Boolean
) {
    fun eat() {
        val type = if (withChocolate) "chocolate" else "plain"
        println("Eating $servingSize $type $name")
    }
}


