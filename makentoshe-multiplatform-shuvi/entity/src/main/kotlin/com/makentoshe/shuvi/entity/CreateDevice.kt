package com.makentoshe.shuvi.entity

/** Device that will be created */
data class CreateDevice(val id: DeviceId?, val title: String, val sensors: List<Sensor>)
