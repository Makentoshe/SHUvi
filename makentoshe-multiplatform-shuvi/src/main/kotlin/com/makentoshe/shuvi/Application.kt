package com.makentoshe.shuvi

import com.makentoshe.shuvi.service.DeviceService
import com.makentoshe.shuvi.service.HelloService
import com.makentoshe.shuvi.service.device.di.DeviceServiceModule
import com.makentoshe.shuvi.service.hello.di.HelloServiceModule
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class RoutingComponent : KoinComponent {
    val helloService by inject<HelloService>()
    val deviceService by inject<DeviceService>()
}

fun main() {
    startKoin { modules(HelloServiceModule, DeviceServiceModule) }
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

        get(component.deviceService.routing) {
            when (call.parameters[component.deviceService.formatParameter]) {
                "", ".html" -> call.respondHtml(HttpStatusCode.OK, component.deviceService.html())
                ".json" -> call.respondText(component.deviceService.json())
                else -> call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
