package ru.andrewgo.demo.mqrequestapp.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MqConfig {

    @Value("\${app.mq.in.queueName}")
    private lateinit var inQueueName: String

    @Value("\${app.mq.out.queueName}")
    private lateinit var outQueueName: String

    @Value("\${app.mq.in.exchangeName}")
    private lateinit var inExchangeName: String

    @Value("\${app.mq.out.exchangeName}")
    private lateinit var outExchangeName: String

    @Bean
    fun inQueue(): Queue {
        return Queue(inQueueName, false)
    }

    @Bean
    fun outQueue(): Queue {
        return Queue(outQueueName, false)
    }

    @Bean
    fun inExchange(): DirectExchange {
        return DirectExchange(inExchangeName)
    }

    @Bean
    fun outExchange(): DirectExchange {
        return DirectExchange(outExchangeName)
    }

    @Bean
    fun inBinding(inQueue: Queue, inExchange: DirectExchange): Binding {
        return BindingBuilder.bind(inQueue).to(inExchange).withQueueName()
    }

    @Bean
    fun outBinding(outQueue: Queue, outExchange: DirectExchange): Binding {
        return BindingBuilder.bind(outQueue).to(outExchange).withQueueName()
    }

    @Bean
    fun jsonMessageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun amqpTemplate(connectionFactory: ConnectionFactory): AmqpTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = jsonMessageConverter()
        return rabbitTemplate
    }
}