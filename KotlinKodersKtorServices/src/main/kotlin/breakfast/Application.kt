package breakfast

import breakfast.plugins.configureHTTP
import breakfast.plugins.configureRouting
import breakfast.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureHTTP()
        configureRouting()
        configureSerialization()
        configureBreakfast()
    }.start(wait = true)
}
