package ru.andrewgo.demo.mqrequestapp

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class MqRequestAppApplication

fun main(args: Array<String>) {
    runApplication<MqRequestAppApplication>(*args)
}

inline fun <reified T> logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}