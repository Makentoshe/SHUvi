package com.makentoshe

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import com.makentoshe.plugins.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "127.0.0.1") {
        configureRouting()
    }.start(wait = true)
}
