package com.makentoshe.shuvi

import com.makentoshe.shuvi.cli.config.ServerConfig
import com.makentoshe.shuvi.di.configureModules
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import kotlinx.serialization.json.Json
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options

fun main(args: Array<String>) {
    val options = Options().apply { com.makentoshe.shuvi.cli.CliOptions.list.forEach { addOption(it) } }
    main(DefaultParser().parse(options, args))
}

private fun main(commandLine: CommandLine) {
    configureModules(commandLine)

    val serverConfig = ServerConfig(commandLine)
    embeddedServer(CIO, port = serverConfig.serverPort, host = serverConfig.serverHost) {
        configureRouting(RoutingComponent())
    }.start(wait = true)
}

private fun Application.configureRouting(component: RoutingComponent) {
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

        route(component.valueSensorService.routing, component.valueSensorService.method) {
            this.handle { component.valueSensorService.handle(call) }
        }

        route(component.getValueService.routing, component.getValueService.method) {
            this.handle { component.getValueService.handle(call) }
        }
    }
}
