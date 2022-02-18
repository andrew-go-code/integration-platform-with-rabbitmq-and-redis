package ru.andrewgo.demo.mqrequestapp.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ru.andrewgo.demo.mqrequestapp.dto.RequestParams
import ru.andrewgo.demo.mqrequestapp.service.MessageService

@RestController
class RequestController(
    private val messageService: MessageService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun request(@RequestBody requestParams: RequestParams) {
        messageService.sendRequestAndGetResponse(requestParams)
    }
}