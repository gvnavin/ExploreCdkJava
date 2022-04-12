package com.gvnavin.infra.explorecdk;

import com.gvnavin.infra.explorecdk.input.Accounts;
import com.gvnavin.infra.explorecdk.constants.Constants;
import com.gvnavin.infra.explorecdk.constants.Stages;
import com.gvnavin.infra.explorecdk.stage.ServiceStage;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.pipelines.CodeBuildStep;
import software.amazon.awscdk.pipelines.CodePipeline;
import software.amazon.awscdk.pipelines.CodePipelineSource;
import software.amazon.awscdk.pipelines.Wave;
import software.amazon.awscdk.services.codecommit.Repository;
import software.amazon.awscdk.services.iam.PolicyStatement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicePipeline {

    private static final String SYNTH = "Synth";
    private static final String BRANCH = "master";
    private static final String NPM_INSTALL_AWS_CDK = "npm install -g aws-cdk";
    private static final String CDK_SYNTH = "cdk synth";
    private static final String CODE_ARTIFACT_TOKEN = "export CODEARTIFACT_AUTH_TOKEN=`aws codeartifact get-authorization-token --domain beta-com-gvnavin-codeartifacts --domain-owner " + Accounts.CODE_ARTIFACTORY_ACCOUNT + " --query authorizationToken --output text`";
    private static final String CODE_ARTIFACT = "arn:aws:codeartifact:ap-south-1:12314324355:domain/beta-com-gvnavin-codeartifacts";
    private static final String CODE_ARTIFACT_REPO = "arn:aws:codeartifact:ap-south-1:12314324355:repository/beta-com-gvnavin-codeartifacts/beta-com-gvnavin-codeartifacts-repository";
    private static final String GET_STS_BEARER_TOKEN = "sts:GetServiceBearerToken";

    @NotNull
    public static CodePipeline setupPipeline(Stack stack, Repository repo) {

        final CodePipeline pipeline = getPipeline(stack, repo);
        final Wave wave = pipeline.addWave(Constants.WAVE_1_ID);

        wave.addStage(ServiceStage.getStage(stack, Constants.WAVE_1_ID, Stages.STAGE_BETA));
//        adding the lambda and table with same name in same region causes cloudformation failure
//        wave.addStage(UserProfileServiceStage.getStage(stack, Constants.WAVE_1_ID, Stages.STAGE_PROD));

        return pipeline;
    }

    @NotNull
    private static CodePipeline getPipeline(Stack stack, Repository repo) {
        return CodePipeline.Builder.create(stack, Constants.PIPELINE_ID)
                .pipelineName(Constants.PIPELINE_NAME)
                .synth(CodeBuildStep.Builder.create(SYNTH)
                        .projectName(Constants.CODE_BUILD_STEP_NAME)
                        .rolePolicyStatements(getPolicyStatements())
                        .input(CodePipelineSource.codeCommit(repo, BRANCH))
                        .installCommands(Arrays.asList(NPM_INSTALL_AWS_CDK))
                        .commands(Arrays.asList(CODE_ARTIFACT_TOKEN, CDK_SYNTH))
                        .build())
                .dockerEnabledForSynth(true)
                .build();
    }

    private static List<? extends PolicyStatement> getPolicyStatements() {

        final Map<String, String> stringEquals = new HashMap();
        stringEquals.put("sts:AWSServiceName", "codeartifact.amazonaws.com");

        Map condition = new HashMap();
        condition.put("StringEquals", stringEquals);

        PolicyStatement ps1 = PolicyStatement.Builder.create()
                .actions(Arrays.asList(GET_STS_BEARER_TOKEN))
                .resources(Arrays.asList("*"))
                .conditions(condition)
                .build();

        PolicyStatement ps2 = PolicyStatement.Builder.create()
                .actions(Arrays.asList(
                        "codeartifact:Get*",
                        "codeartifact:Describe*",
                        "codeartifact:Read*",
                        "codeartifact:List*"
                ))
                .resources(Arrays.asList(CODE_ARTIFACT, CODE_ARTIFACT_REPO))
                .build();

        return Arrays.asList(ps1, ps2);
    }

}
