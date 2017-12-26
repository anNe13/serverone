package services

import com.github.kevinsawicki.http.HttpRequest

import org.json.JSONObject

const val APP_PARAMETER = "app=studentproject_nackademin"
const val BASE_URL = "https://brottsplatskartan.se/api"

fun getAreas() : List<String>{
    val result = HttpRequest.get("$BASE_URL/areas?$APP_PARAMETER").body()

    return JSONObject(result).getJSONObject("data").getJSONArray("areas")
            .map { JSONObject(it.toString()).getString("administrative_area_level_1")}
}

fun getLastEvents(): List<String> {
    val result = HttpRequest.get("$BASE_URL/events?limit=20&area=&location=&type=&page=1$APP_PARAMETER").body()

    val theList = JSONObject(result).getJSONArray("data")
            .map { ((it.toString())) }
println(theList)
    return (theList)
}