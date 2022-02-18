package ru.andrewgo.demo.mqrequestapp.service

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.andrewgo.demo.mqrequestapp.dto.RequestMessage
import ru.andrewgo.demo.mqrequestapp.dto.RequestParams
import ru.andrewgo.demo.mqrequestapp.dto.ResponseMessage
import ru.andrewgo.demo.mqrequestapp.logger
import java.util.*

@Component
class MessageService(
    val amqpTemplate: AmqpTemplate,
    val responseService: ResponseMessageService
) {

    @Value("\${app.mq.in.queueName}")
    private lateinit var inQueueName: String

    @Value("\${app.mq.in.exchangeName}")
    private lateinit var inExchangeName: String

    @Value("\${app.mq.out.queueName}")
    private lateinit var outQueueName: String

    @Value("\${app.mq.out.exchangeName}")
    private lateinit var outExchangeName: String

//    @Scheduled(fixedDelay = 1000)
    fun sendRequestAndGetResponse(requestParams: RequestParams) {
        val messageId = UUID.randomUUID().toString()
        amqpTemplate.convertAndSend(outExchangeName, outQueueName, RequestMessage.createMessage(requestParams.appId)) {
            it.messageProperties.correlationId = messageId
            it
        }
        val response = responseService.getResponseById(messageId, requestParams)
        logger<MessageService>().info("got response $response")
    }

    @RabbitListener(queues = ["\${app.mq.in.queueName}"])
    fun receiveResponses(requestMessage: ResponseMessage, amqpMessage: Message) {
        logger<MessageService>().info("listener receives message id: ${requestMessage.id}")
        responseService.saveResponse(requestMessage)
    }
}