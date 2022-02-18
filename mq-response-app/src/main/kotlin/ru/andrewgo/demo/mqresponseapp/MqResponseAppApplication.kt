package ru.andrewgo.demo.mqresponseapp

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MqResponseAppApplication

fun main(args: Array<String>) {
    runApplication<MqResponseAppApplication>(*args)
}

inline fun <reified T> logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}
