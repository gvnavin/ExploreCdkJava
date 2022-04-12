package com.gvnavin.infra.explorecdk;

import com.gvnavin.infra.explorecdk.constants.Constants;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.pipelines.CodePipeline;
import software.amazon.awscdk.services.codecommit.Repository;
import software.constructs.Construct;

public class ServiceStack extends Stack {

    public ServiceStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public ServiceStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        final Repository repo = getRepo();
        final CodePipeline pipeline = ServicePipeline.setupPipeline(this, repo);

    }

    @NotNull
    private Repository getRepo() {
        return Repository.Builder.create(this, Constants.REPO_ID)
                .repositoryName(Constants.REPO_NAME)
                .build();
    }

}
