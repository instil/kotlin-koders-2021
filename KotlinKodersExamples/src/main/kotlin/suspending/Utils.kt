package suspending

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun printInDetail(item: String) {
    fun timeNow(): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return formatter.format(LocalTime.now())
    }

    fun timeAndThread(item: String) = "[$item at ${timeNow()} on ${Thread.currentThread().id}]"

    println(timeAndThread(item))
}

fun buildHttpClient() = HttpClient(CIO) {
    install(JsonFeature)
    defaultRequest {
        host = "0.0.0.0"
        port = 8080
        url {
            protocol = URLProtocol.HTTP
        }
    }
}
