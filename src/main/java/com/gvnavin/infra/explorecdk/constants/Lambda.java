package com.gvnavin.infra.explorecdk.constants;

import static com.gvnavin.infra.explorecdk.constants.Constants.*;

public class Lambda {

    public static final String LAMBDA_FUNCTION_ID = USE_NAME + "LambdaFunction";
    public static final String LAMBDA_FUNCTION_NAME = LAMBDA_FUNCTION_ID;

    public static final String LAMBDA_FUNCTION_INTEGRATION_ID = LAMBDA_FUNCTION_ID + "Integrations";

    public static final String HTTP_API_ID = LAMBDA_FUNCTION_ID + "HttpApi";
    public static final String HTTP_API_BASE_PATH = "/"+COMPONENT_PKG_NAME;
    public static final String PLURAL_HTTP_API_BASE_PATH = "/"+COMPONENT_PKG_NAME+"s";

    public static final String HTTP_STAGE_ID = LAMBDA_FUNCTION_ID + "Stage";

}
