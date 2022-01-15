package com.makentoshe.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
//        install(StatusPages) {
//            exception<AuthenticationException> { cause ->
//                call.respond(HttpStatusCode.Unauthorized)
//            }
//            exception<AuthorizationException> { cause ->
//                call.respond(HttpStatusCode.Forbidden)
//            }
//
//        }
    }
}

//class AuthenticationException : RuntimeException()
//class AuthorizationException : RuntimeException()
