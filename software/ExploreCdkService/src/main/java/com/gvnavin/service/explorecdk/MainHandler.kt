package com.gvnavin.service.explorecdk

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse

import software.amazon.lambda.powertools.metrics.Metrics
import software.amazon.lambda.powertools.tracing.CaptureMode.DISABLED
import software.amazon.lambda.powertools.tracing.Tracing

class MainHandler : RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    @Tracing(captureMode = DISABLED)
    @Metrics(captureColdStart = true)
    override fun handleRequest(input: APIGatewayV2HTTPEvent, context: Context): APIGatewayV2HTTPResponse {

        println(input)

        return APIGatewayV2HTTPResponse.builder()
            .withBody("{}")
            .withStatusCode(500)
            .build()
    }

}
