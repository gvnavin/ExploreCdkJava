package com.gvnavin.infra.explorecdk.lambda;

import com.gvnavin.infra.explorecdk.input.Accounts;
import com.gvnavin.infra.explorecdk.constants.Constants;
import com.gvnavin.infra.explorecdk.constants.Lambda;
import com.gvnavin.infra.explorecdk.constants.Regions;
import com.gvnavin.infra.explorecdk.apigw.Routes;
import com.gvnavin.infra.explorecdk.ddb.DDB;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.apigatewayv2.alpha.HttpApi;
import software.amazon.awscdk.services.apigatewayv2.alpha.HttpStage;
import software.amazon.awscdk.services.apigatewayv2.integrations.alpha.HttpLambdaIntegration;
import software.amazon.awscdk.services.iam.Effect;
import software.amazon.awscdk.services.iam.Policy;
import software.amazon.awscdk.services.iam.PolicyStatement;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.logs.RetentionDays;
import software.constructs.Construct;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ServiceLambdaStack extends Stack {

    private static final String PATH = "software/" + Constants.SERVICE_NAME + "/build/libs/" + Constants.SERVICE_NAME + "-1.0-SNAPSHOT-all.jar";
    public static final String HANDLER = "com.gvnavin.service." + Constants.COMPONENT_PKG_NAME + ".MainHandler";
    public static final String DDB_RESOURCE = "arn:aws:dynamodb:" + Regions.DEFAULT_REGION + ":" + Accounts.WAVE_1_ACCOUNT_BETA + " :table/*" ;

    public ServiceLambdaStack(final Construct scope, final String id, final String stage) {
        this(scope, id, null, stage);
    }

    public ServiceLambdaStack(final Construct scope, final String id, final StackProps props, final String stage) {
        super(scope, id, props);

        DDB.setupTables(this);
        Function lambdaFunction = getFunction();

        HttpLambdaIntegration lambdaIntegration = HttpLambdaIntegration.Builder
                .create(Lambda.LAMBDA_FUNCTION_INTEGRATION_ID, lambdaFunction)
                .build();

        HttpApi httpApi = HttpApi.Builder
                .create(this, Lambda.HTTP_API_ID)
                .build();

        Routes.setupRoutes(lambdaIntegration, httpApi);

        HttpStage.Builder.create(this, Lambda.HTTP_STAGE_ID)
                .httpApi(httpApi)
                .stageName(stage)
//               $default will hold the deployment, beta will not be enabled the auto deploy
//               .autoDeploy(true)
                .build();
    }


    @NotNull
    private Function getFunction() {
        Function function = Function.Builder.create(this, Lambda.LAMBDA_FUNCTION_ID)
                .functionName(Lambda.LAMBDA_FUNCTION_NAME)
                .runtime(Runtime.JAVA_11)
                .code(Code.fromAsset(PATH))
                .handler(HANDLER)
                .memorySize(1024)
                .timeout(Duration.seconds(10))
                .logRetention(RetentionDays.ONE_WEEK)
                .build();

//        addPermissionsToFunction(function);

        return function;
    }

    private void addPermissionsToFunction(Function function) {
        List<String> ddbActions = Arrays.asList(
                "dynamodb:GetItem",
                "dynamodb:BatchGetItem",
                "dynamodb:Query",
                "dynamodb:PutItem",
                "dynamodb:UpdateItem",
                "dynamodb:BatchWriteItem"
        );

        PolicyStatement policyStatement = PolicyStatement.Builder.create()
                .sid("test")
                .effect(Effect.ALLOW)
                .resources(Arrays.asList(DDB_RESOURCE))
                .actions(ddbActions)
                .build();

        Policy policy = Policy.Builder.create(this, Constants.DDB_POLICY_ID)
                .policyName(Constants.DDB_POLICY_NAME)
                .statements(Arrays.asList(policyStatement))
                .build();
        Objects.requireNonNull(function.getRole()).attachInlinePolicy(policy);

        System.out.println(policyStatement);
        System.out.println(policy);
        System.out.println(function.getRole());
    }

}