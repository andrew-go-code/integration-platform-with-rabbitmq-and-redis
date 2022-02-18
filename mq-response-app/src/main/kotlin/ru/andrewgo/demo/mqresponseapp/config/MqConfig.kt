package ru.andrewgo.demo.mqresponseapp.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
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
    fun outBinding(outQueue: Queue, outExchange: DirectExchange): Binding {
        return BindingBuilder.bind(outQueue).to(outExchange).withQueueName()
    }

    @Bean
    fun jackson2JsonMessageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

}