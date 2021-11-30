package breakfast.model

class Breakfast(private val beverage: Beverage, private val food: Food) {
    fun consume() {
        beverage.drink()
        food.eat()
    }
}
