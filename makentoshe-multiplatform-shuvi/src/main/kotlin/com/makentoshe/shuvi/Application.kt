package com.makentoshe.shuvi

import com.makentoshe.shuvi.database.di.MongoDatabaseModule
import com.makentoshe.shuvi.service.CreateDeviceService
import com.makentoshe.shuvi.service.DeviceService
import com.makentoshe.shuvi.service.HelloService
import com.makentoshe.shuvi.service.device.create.di.CreateDeviceServiceModule
import com.makentoshe.shuvi.service.device.di.DeviceServiceModule
import com.makentoshe.shuvi.service.hello.di.HelloServiceModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class RoutingComponent : KoinComponent {
    val helloService by inject<HelloService>()
    val deviceService by inject<DeviceService>()
    val createDeviceService by inject<CreateDeviceService>()
}

fun main() {
    startKoin { modules(MongoDatabaseModule, HelloServiceModule, DeviceServiceModule, CreateDeviceServiceModule) }
    val routingComponent = RoutingComponent()
    embeddedServer(CIO, port = 8080, host = "127.0.0.1") { configureRouting(routingComponent) }.start(wait = true)
}

fun Application.configureRouting(component: RoutingComponent) {
    install(ContentNegotiation) {
        json(Json { prettyPrint = true; isLenient = true })
    }

    routing {

        get("/") {
            call.respondText("Hello World!")
        }

        get(component.helloService.routing) {
            call.respondText(component.helloService.sayHello())
        }

        get(component.deviceService.routing) {
            component.deviceService.handle(call)
        }

        post(component.createDeviceService.routing) {
            component.createDeviceService.handle(call)
        }
    }
}
