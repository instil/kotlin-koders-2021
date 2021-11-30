package suspending

import breakfast.model.Beverage
import breakfast.model.Breakfast
import breakfast.model.Food
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun main() = runBlocking {
    printInDetail("Program starts")
    val client = buildHttpClient()
    client.use {
        val breakfast = orderBreakfast(client)
        printInDetail("Breakfast ordered")
        breakfast.consume()
    }
    printInDetail("Program ends")
}

suspend fun orderBreakfast(client: HttpClient): Breakfast = withContext(Dispatchers.IO) {
    printInDetail("Ordering breakfast")

    val beverage: Beverage = orderBeverage(client)
    val food: Food = orderFood(client, beverage)
    Breakfast(beverage, food)
}

private suspend fun orderFood(it: HttpClient, beverage: Beverage): Food =
    it.post("/breakfast/food") {
        body = beverage
        contentType(ContentType.Application.Json)
    }

private suspend fun orderBeverage(it: HttpClient): Beverage =
    it.get("/breakfast/beverage") {
        accept(ContentType.Application.Json)
    }
