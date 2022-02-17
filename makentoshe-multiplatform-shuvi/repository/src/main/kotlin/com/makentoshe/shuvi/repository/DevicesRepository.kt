package com.makentoshe.shuvi.repository

import com.makentoshe.shuvi.response.repository.GetDevicesResponse

interface DevicesRepository {

    fun getDevices(): GetDevicesResponse
}