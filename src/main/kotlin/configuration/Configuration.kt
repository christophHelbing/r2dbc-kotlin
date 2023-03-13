package configuration

object Configuration {
    val driver = System.getenv("DRIVER")
    val host = System.getenv("HOST")
    val port = System.getenv("PORT").toInt()
    val serviceName = System.getenv("SERVICE_NAME")
    val userLegacy = System.getenv("USER_LEGACY")
    val passwordlegacy = System.getenv("PASSWORD_LEGACY")
}
