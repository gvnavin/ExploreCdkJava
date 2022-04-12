package com.gvnavin.infra.explorecdk.constants;

import static com.gvnavin.infra.explorecdk.input.Input.COMPONENT_NAME;

public class Constants {

    public static final String COMPONENT_PKG_NAME = COMPONENT_NAME.toLowerCase();

    public static final String LIB_NAME = COMPONENT_NAME + "Lib";
    public static final String SERVICE_NAME = COMPONENT_NAME + "Service";

    public static final String USE_NAME = SERVICE_NAME;

    public static final String REPO_ID = USE_NAME;
    public static final String REPO_NAME = REPO_ID;

    public static final String PIPELINE_ID = USE_NAME + "Pipeline";
    public static final String PIPELINE_NAME = PIPELINE_ID;

    //We might not do multiple wave in near future. Hence, stick with 1 wave and 1 region
    public static final String WAVE_1_ID = USE_NAME + "IN";

    public static final String DDB_POLICY_ID = USE_NAME + "DdbPolicy";
    public static final String DDB_POLICY_NAME = USE_NAME + "DdbPolicy";

    public static final String CODE_BUILD_STEP_NAME = USE_NAME + "CodeBuild";


}
