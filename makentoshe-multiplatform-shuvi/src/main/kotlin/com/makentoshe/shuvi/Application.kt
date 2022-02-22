package com.makentoshe.shuvi

import com.makentoshe.shuvi.database.di.MongoDatabaseModule
import com.makentoshe.shuvi.service.device.create.di.CreateDeviceServiceModule
import com.makentoshe.shuvi.service.device.delete.di.DeleteDeviceServiceModule
import com.makentoshe.shuvi.service.device.di.DeviceServiceModule
import com.makentoshe.shuvi.service.devices.di.DevicesServiceModule
import com.makentoshe.shuvi.service.sensor.create.di.CreateSensorServiceModule
import com.makentoshe.shuvi.service.sensor.di.GetSensorServiceModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(
            MongoDatabaseModule,
            DevicesServiceModule,
            CreateDeviceServiceModule,
            DeviceServiceModule,
            DeleteDeviceServiceModule,
            GetSensorServiceModule,
            CreateSensorServiceModule,
        )
    }
    val routingComponent = RoutingComponent()
    embeddedServer(CIO, port = 8080, host = "127.0.0.1") { configureRouting(routingComponent) }.start(wait = true)
}

fun Application.configureRouting(component: RoutingComponent) {
    install(ContentNegotiation) {
        json(Json { prettyPrint = true; isLenient = true; explicitNulls = true })
    }

    routing {

        get("/") {
            call.respondText("Hello World!")
        }

        get(component.devicesService.routing) {
            component.devicesService.handle(call)
        }

        get(component.deviceService.routing) {
            component.deviceService.handle(call)
        }

        post(component.createDeviceService.routing) {
            component.createDeviceService.handle(call)
        }

        post(component.deleteDeviceService.routing) {
            component.deleteDeviceService.handle(call)
        }

        get(component.getSensorService.routing) {
            component.getSensorService.handle(call)
        }

        post(component.createSensorService.routing) {
            component.createSensorService.handle(call)
        }
    }
}
