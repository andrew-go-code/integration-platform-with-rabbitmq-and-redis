package ru.andrewgo.demo.gatlingtest

import com.fasterxml.jackson.databind.ObjectMapper
import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http

class BasicSimulation : Simulation() {

    private val ports = System.getenv("appPorts").split(",") //listOf(8001, 8002)

    init {
        setUp(
            ports.map { port ->
                scenario("Simple Request for app on port $port Scenario")
                    .exec(
                        http("simple request")
                            .post("/")
                            .header("content-type", "application/json")
                            .body(StringBody {
                                ObjectMapper().writeValueAsString(RequestParams(100, 300, port))
                            })
                    )
                    .injectOpen(atOnceUsers(10))
                    .protocols(
                        http.baseUrl("http://localhost:$port")
                    )
            }
        )
    }
}