package com.makentoshe.shuvi

import com.makentoshe.shuvi.service.CreateDeviceService
import com.makentoshe.shuvi.service.DeleteDeviceService
import com.makentoshe.shuvi.service.DeviceService
import com.makentoshe.shuvi.service.DevicesService
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