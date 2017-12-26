import spark.Spark.*
import ws.EventWs


fun main(args: Array<String>) {
    val process = ProcessBuilder()
    val port: Int?
    if (process.environment()["PORT"] != null) {
        port = Integer.parseInt(process.environment()["PORT"])
    } else {
        port = 8080
    }




    port(port)
  //  ipAddress("92.254.184.174")
   // staticFileLocation("/public")
    webSocket("/event", EventWs::class.java)
    init()
}