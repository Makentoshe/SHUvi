package com.makentoshe.shuvi

import com.makentoshe.shuvi.service.device.CreateDeviceService
import com.makentoshe.shuvi.service.device.DeleteDeviceService
import com.makentoshe.shuvi.service.device.DeviceService
import com.makentoshe.shuvi.service.device.DevicesService
import com.makentoshe.shuvi.service.sensor.CreateSensorService
import com.makentoshe.shuvi.service.sensor.GetSensorService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RoutingComponent : KoinComponent {
    val devicesService by inject<DevicesService>()
    val createDeviceService by inject<CreateDeviceService>()
    val deviceService by inject<DeviceService>()
    val deleteDeviceService by inject<DeleteDeviceService>()

    val getSensorService by inject<GetSensorService>()
    val createSensorService by inject<CreateSensorService>()
}