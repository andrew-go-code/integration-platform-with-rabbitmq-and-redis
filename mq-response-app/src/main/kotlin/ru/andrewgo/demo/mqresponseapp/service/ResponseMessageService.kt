package ru.andrewgo.demo.mqresponseapp.service

import org.springframework.stereotype.Component
import ru.andrewgo.demo.mqresponseapp.dto.RequestMessage
import ru.andrewgo.demo.mqresponseapp.dto.ResponseMessage
import java.time.LocalDateTime

@Component
class ResponseMessageService{

    fun getResponseMessage(requestMessage: RequestMessage, correlationId: String): ResponseMessage {
        return ResponseMessage(
            id = correlationId,
            timeCreated = LocalDateTime.now().toLocalTime().toString(),
            timeRequestCreated = requestMessage.timeCreated,
            fromAppId = requestMessage.appId
        )
    }
}