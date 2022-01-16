package com.makentoshe.shuvi

import com.makentoshe.shuvi.service.HelloService
import com.makentoshe.shuvi.service.hello.di.HelloServiceModule
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class RoutingComponent : KoinComponent {
    val helloService by inject<HelloService>()
}

fun main() {
    startKoin { modules(HelloServiceModule) }
    val routingComponent = RoutingComponent()
    embeddedServer(CIO, port = 8080, host = "127.0.0.1") { configureRouting(routingComponent) }.start(wait = true)
}

fun Application.configureRouting(component: RoutingComponent) {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get(component.helloService.routing) {
            call.respondText(component.helloService.sayHello())
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
