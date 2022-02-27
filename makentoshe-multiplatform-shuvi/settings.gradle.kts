rootProject.name = "Shuvi"

include("common")
include("entity")

include("database")
include("database-mongo")

include("service")
include("repository")

include("service-devices")
include("repository-devices")

include("service-device-create")
include("repository-device-create")

include("service-device")
include("repository-device-get")

include("service-device-delete")
include("repository-device-delete")
include("service-sensor")
include("repository-sensor-get")
include("service-sensor-create")
include("repository-sensor-create")
include("repository-crossref-device-sensor-create")
