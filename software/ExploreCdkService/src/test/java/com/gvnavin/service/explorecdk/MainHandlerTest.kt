package com.gvnavin.service.explorecdk

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test

class MainHandlerTest {

    @Test
    fun test() {

        val apiGatewayV2HTTPEvent =
            ObjectMapper().readValue(aPIGatewayProxyRequestEvent, APIGatewayV2HTTPEvent::class.java)

        val mainHandler = MainHandler()

        val response = mainHandler.handleRequest(apiGatewayV2HTTPEvent, TestContext())
        println(response)

    }

}