package breakfast

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import java.time.LocalTime
import java.util.*
import java.util.Locale.FRANCE

import breakfast.model.Beverage
import breakfast.model.BeverageType.*
import breakfast.model.BeverageSize.*
import breakfast.model.Food
import io.ktor.request.*

fun selectBeverage(): Beverage {
    fun inFrance() = Locale.getDefault() == FRANCE
    fun beforeNoon() = LocalTime.now().isBefore(LocalTime.NOON)

    val type = if (inFrance()) CafeAuLait else Americano
    val size = if (beforeNoon()) Large else Medium
    return Beverage(type, size)
}

fun selectFood(beverage: Beverage): Food {
    val serving = if (beverage.size == Large) 3 else 2
    return if (beverage.type == CafeAuLait) {
        Food("Croissants", serving, true)
    } else {
        Food("Pancakes", serving, false)
    }
}

fun Application.configureBreakfast() {
    routing {
        get("/breakfast/beverage") {
            call.respond(selectBeverage())
        }
        post("/breakfast/food") {
            val beverage = call.receive<Beverage>()
            call.respond(selectFood(beverage))
        }
    }
}
