package ru.andrewgo.demo.mqresponseapp.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class ResponseMessage(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("timeCreated")
    val timeCreated: String,
    @JsonProperty("timeRequestCreated")
    val timeRequestCreated: String,
    @JsonProperty("fromPort")
    val fromAppId: String
) : Serializable