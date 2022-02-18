package ru.andrewgo.demo.mqresponseapp.service

import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.andrewgo.demo.mqresponseapp.dto.RequestMessage
import ru.andrewgo.demo.mqresponseapp.logger


@Component
class ResponseService(
        private val rabbitTemplate: RabbitTemplate,
        private val responseMessageService: ResponseMessageService,
        @Value("\${app.fakeDelayInMs}") private var fakeDelayInMs: Long
) {

    @Value("\${app.mq.out.queueName}")
    private lateinit var outQueueName: String

    @Value("\${app.mq.in.queueName}")
    private lateinit var inQueueName: String

    @Value("\${app.mq.in.exchangeName}")
    private lateinit var inExchangeName: String

    @Value("\${app.mq.out.exchangeName}")
    private lateinit var outExchangeName: String


    @RabbitListener(queues = ["\${app.mq.in.queueName}"])
    fun receivedMessage(requestMessage: RequestMessage, amqpMessage: Message) {
        logger<ResponseService>().info("received message $requestMessage")
        Thread.sleep(fakeDelayInMs)
        val responseMessage =
            responseMessageService.getResponseMessage(requestMessage, amqpMessage.messageProperties.correlationId)
        rabbitTemplate.convertAndSend(outExchangeName, outQueueName, responseMessage) {
            it.messageProperties.correlationId = amqpMessage.messageProperties.correlationId
            it
        }
    }

}