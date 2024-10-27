plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "hakaton-autum-2024-api"

include("Authentication-Service")
include("Eureka-Server")
include("Gateway")
include("Migration-Service")
include("Kanban-Service")
include("File-Export-Service")
include("Profile-Service")
