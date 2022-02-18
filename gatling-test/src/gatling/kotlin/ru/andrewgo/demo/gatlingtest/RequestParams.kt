package ru.andrewgo.demo.gatlingtest

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestParams(
    @JsonProperty("delayInMs")
    val delayInMs: Long,
    @JsonProperty("expirationTimeInSec")
    val expirationTimeInSec: Long,
    @JsonProperty("appId")
    val appId: String
)