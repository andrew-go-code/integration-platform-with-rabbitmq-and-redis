package ru.andrewgo.demo.mqresponseapp.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class RequestMessage(
    @JsonProperty("timeCreated")
    val timeCreated: String,
    @JsonProperty("appId")
    val appId: String
) : Serializable
