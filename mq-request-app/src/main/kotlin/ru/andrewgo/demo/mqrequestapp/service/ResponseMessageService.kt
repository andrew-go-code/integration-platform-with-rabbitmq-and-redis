package ru.andrewgo.demo.mqrequestapp.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.andrewgo.demo.mqrequestapp.dto.RequestParams
import ru.andrewgo.demo.mqrequestapp.dto.ResponseMessage
import ru.andrewgo.demo.mqrequestapp.logger
import ru.andrewgo.demo.mqrequestapp.repository.RequestMessageRedisRepository
import java.time.LocalDateTime

@Component
class ResponseMessageService(
    val redisRepository: RequestMessageRedisRepository,
    @Value("\${app.cache.delayInMs}")
    private var delayInMs: Long,
    @Value("\${app.cache.expirationTimeInSec}")
    private var expirationTimeInSec: Long,
) {
    companion object {
        private val LOGGER = logger<ResponseMessageService>()
    }

    fun getResponseById(id: String): ResponseMessage? {
        return tryToFindResponseMessage(id, expirationTimeInSec, delayInMs)
    }

    fun getResponseById(id: String, requestParams: RequestParams): ResponseMessage? {
        return tryToFindResponseMessage(id, requestParams.expirationTimeInSec, requestParams.delayInMs)
    }

    private fun tryToFindResponseMessage(
        id: String,
        expirationTimeInSec: Long,
        delayInMs: Long
    ): ResponseMessage? {
        val stopTime = LocalDateTime.now().plusSeconds(expirationTimeInSec)
        while (LocalDateTime.now().isBefore(stopTime)) {
            if (redisRepository.existsById(id)) {
                return redisRepository.findById(id).get()
            }
            Thread.sleep(delayInMs)
        }
        LOGGER.warn("Message id: $id not found during $expirationTimeInSec seconds")
        return null
    }

    fun saveResponse(responseMessage: ResponseMessage) {
        redisRepository.save(responseMessage)
    }
}