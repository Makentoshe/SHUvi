rootProject.name = "Shuvi"

include("monolith")
include("monolith:common")
include("monolith:entity")

include("monolith:database")
include("monolith:database-mongo")
include("monolith:database-postgres")

include("monolith:repository")
include("monolith:repository-devices")
include("monolith:repository-device-create")
include("monolith:repository-device-get")
include("monolith:repository-device-delete")
include("monolith:repository-sensor-get")
include("monolith:repository-sensor-create")
include("monolith:repository-crossref-device-sensor-create")

include("monolith:service")
include("monolith:service-devices")
include("monolith:service-device-create")
include("monolith:service-device")
include("monolith:service-device-delete")
include("monolith:service-sensor")
include("monolith:service-sensor-create")
include("monolith:service-sensor-value")
