package ws

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import models.Message
import org.eclipse.jetty.websocket.api.Session
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect
import org.eclipse.jetty.websocket.api.annotations.WebSocket
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose
import java.io.IOException
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage
import services.getAreasMessage
import services.getLatestMessage


@WebSocket
class EventWs {
    val sessions = mutableListOf<Session>()

    @OnWebSocketConnect
    fun connected(session: Session) {
        println("session $session connected")
        emit(session, getAreasMessage())
        sessions.add(session)
    }

    @OnWebSocketClose
    fun closed(session: Session, statusCode: Int, reason: String) {
        sessions.remove(session)
    }

    @OnWebSocketMessage
    @Throws(IOException::class)
    fun message(session: Session, message: String) {
        val json = ObjectMapper().readTree(message)
        when (json.get("type").asText()){
            "latest" -> {
                emit(session, getLatestMessage())
            }
            "areas" -> {
                emit(session, getAreasMessage())
            }
        }
        println("Got: " + message)   // Print message
      //  session.remote.sendString(message) // and send it back
    }

    fun emit(session: Session, message: Message) {
        session.remote.sendString(jacksonObjectMapper().writeValueAsString(message))
    }

    fun broadcast(message: Message) {
        sessions.forEach() { emit(it, message) }
    }




}