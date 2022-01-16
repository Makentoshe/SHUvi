package com.makentoshe.shuvi.repository

interface DeviceRepository {
    fun getDevices(): List<String>
}