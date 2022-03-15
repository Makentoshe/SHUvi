object Module {

    val monolith = Monolith

}

object Monolith {

    const val common = ":monolith:common"
    const val entity = ":monolith:entity"

    const val database = ":monolith:database"
    const val databaseMongo = ":monolith:database-mongo"
    const val databasePostgres = ":monolith:database-postgres"

    const val repository = ":monolith:repository"
    const val repositoryDevices = ":monolith:repository-devices"
    const val repositoryCrossrefSensorDeviceCreate = ":monolith:repository-crossref-device-sensor-create"
    const val repositoryDeviceCreate = ":monolith:repository-device-create"
    const val repositoryDevice = ":monolith:repository-device-get"
    const val repositoryDeviceDelete = ":monolith:repository-device-delete"
    const val repositorySensorGet = ":monolith:repository-sensor-get"
    const val repositorySensorCreate = ":monolith:repository-sensor-create"

    const val service = ":monolith:service"
    const val serviceDevices = ":monolith:service-devices"
    const val serviceDeviceCreate = ":monolith:service-device-create"
    const val serviceDevice = ":monolith:service-device"
    const val serviceDeviceDelete = ":monolith:service-device-delete"
    const val serviceSensorGet = ":monolith:service-sensor"
    const val serviceSensorCreate = ":monolith:service-sensor-create"
    const val serviceSensorValue = ":monolith:service-sensor-value"

    override fun toString() = ":monolith"
}