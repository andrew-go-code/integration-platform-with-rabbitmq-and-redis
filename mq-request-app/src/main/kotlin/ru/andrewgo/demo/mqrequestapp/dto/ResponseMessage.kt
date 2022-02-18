package ru.andrewgo.demo.mqrequestapp.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

@RedisHash
data class ResponseMessage(
    @Id
    @JsonProperty("id")
    val id: String,
    @JsonProperty("timeCreated")
    val timeCreated: String,
    @JsonProperty("timeRequestCreated")
    val timeRequestCreated: String,
    @JsonProperty("fromAppId")
    val fromAppId: String
) : Serializable