package services

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import models.Message

fun getAreasMessage(): Message{
    return Message("areas", getAreas())
}

fun getLatestMessage(): Message{
    return Message("latest", getLastEvents())
}