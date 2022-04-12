package com.gvnavin.infra.explorecdk;

import com.gvnavin.infra.explorecdk.input.Accounts;
import com.gvnavin.infra.explorecdk.constants.Regions;
import com.gvnavin.infra.explorecdk.constants.Stacks;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class ServiceCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        new ServiceStack(app, Stacks.STACK_ID, StackProps.builder()
                .env(Environment.builder()
                        .account(Accounts.PIPELINE_ACCOUNT)
                        .region(Regions.DEFAULT_REGION)
                        .build())
                .build()
        );

        app.synth();
    }
}

