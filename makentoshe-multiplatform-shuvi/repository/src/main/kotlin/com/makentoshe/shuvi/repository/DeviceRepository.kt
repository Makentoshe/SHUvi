package com.makentoshe.shuvi.repository

import com.makentoshe.shuvi.response.repository.GetDevicesResponse

interface DeviceRepository {

    fun getDevices(): GetDevicesResponse
}