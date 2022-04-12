package com.gvnavin.infra.explorecdk.stage;

import com.gvnavin.infra.explorecdk.constants.Stacks;
import com.gvnavin.infra.explorecdk.lambda.ServiceLambdaStack;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.Stage;
import software.amazon.awscdk.StageProps;
import software.constructs.Construct;

public class ServiceStage extends Stage {

    public ServiceStage(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public ServiceStage(final Construct scope, final String id, final StageProps props) {
        super(scope, id, props);

        Stack lambdaStack = new ServiceLambdaStack(this, Stacks.LAMBDA_STACK_ID, id);
    }

    @NotNull
    public static ServiceStage getStage(Stack stack, String wave, String stage) {
        return new ServiceStage(stack, stage, StageFactory.getStageProps(wave, stage));
    }

}