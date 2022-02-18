package ru.andrewgo.demo.mqrequestapp.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.andrewgo.demo.mqrequestapp.dto.ResponseMessage

@Repository
interface RequestMessageRedisRepository : CrudRepository<ResponseMessage, String>