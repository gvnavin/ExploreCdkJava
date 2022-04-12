package com.gvnavin.infra.explorecdk.apigw;

import com.gvnavin.infra.explorecdk.constants.Lambda;
import software.amazon.awscdk.services.apigatewayv2.alpha.AddRoutesOptions;
import software.amazon.awscdk.services.apigatewayv2.alpha.HttpApi;
import software.amazon.awscdk.services.apigatewayv2.alpha.HttpMethod;
import software.amazon.awscdk.services.apigatewayv2.integrations.alpha.HttpLambdaIntegration;

import java.util.Arrays;

public class Routes {
    public static void setupRoutes(HttpLambdaIntegration lambdaIntegration, HttpApi httpApi) {

        httpApi.addRoutes(AddRoutesOptions.builder()
                .path(Lambda.PLURAL_HTTP_API_BASE_PATH)
                .methods(Arrays.asList(HttpMethod.GET))
                .integration(lambdaIntegration)
                .build());

        httpApi.addRoutes(AddRoutesOptions.builder()
                .path(Lambda.PLURAL_HTTP_API_BASE_PATH + "/{id}")
                .methods(Arrays.asList(HttpMethod.GET))
                .integration(lambdaIntegration)
                .build());

        httpApi.addRoutes(AddRoutesOptions.builder()
                .path(Lambda.PLURAL_HTTP_API_BASE_PATH)
                .methods(Arrays.asList(HttpMethod.POST))
                .integration(lambdaIntegration)
                .build());

        httpApi.addRoutes(AddRoutesOptions.builder()
                .path(Lambda.PLURAL_HTTP_API_BASE_PATH + "/{id}")
                .methods(Arrays.asList(HttpMethod.PUT))
                .integration(lambdaIntegration)
                .build());
    }

}
