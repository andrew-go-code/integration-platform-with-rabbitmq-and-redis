package ru.andrewgo.demo.mqrequestapp.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDateTime

data class RequestMessage(
    @JsonProperty("timeCreated")
    val timeCreated: String,
    @JsonProperty("appId")
    val appId: String
) : Serializable {
    companion object {
        fun createMessage(appId: String): RequestMessage {
            return RequestMessage(
                timeCreated = LocalDateTime.now().toLocalTime().toString(),
                appId = appId
            )
        }
    }
}
