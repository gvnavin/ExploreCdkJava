package com.gvnavin.infra.explorecdk.ddb;

import com.gvnavin.infra.explorecdk.constants.GSI;
import com.gvnavin.infra.explorecdk.constants.Tables;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.*;

public class DDB {

    public static void setupTables(Stack stack) {

        //    private static final String INDEX_HASH_KEY = "";
        Table tableSurveySave = TableUtils.createTable(stack, Tables.SURVEY_SAVE, "survey_id", "");
        GSIUtils.addGSI(tableSurveySave, GSI.USERNAME_GSI, "username", "");

        TableUtils.createTable(stack, Tables.SURVEY_AGGREGATED_RESPONSES, "survey_id", "");
        TableUtils.createTable(stack, Tables.SURVEY_PUBLISH, "survey_id", "");
        TableUtils.createTable(stack, Tables.SURVEY_QUESTION_AGGREGATED_RESPONSES, "survey_id", "question_id");

        Table tableSurveyResponse = TableUtils.createTable(stack, Tables.SURVEY_RESPONSE, "survey_id_user_id_response_key", "timestamp");
        GSIUtils.addGSI(tableSurveyResponse, GSI.SURVEY_ID_TIMESTAMP_INDEX_GSI, "survey_id", "timestamp");
        GSIUtils.addGSI(tableSurveyResponse, GSI.USER_ID_SURVEY_ID_INDEX, "user_id", "survey_id");
        GSIUtils.addGSI(tableSurveyResponse, GSI.TIMESTAMP_INDEX, "month_year", "");

        Table table = TableUtils.createTable(stack, Tables.SURVEY_TEXT_QUESTION_AGGREGATED_RESPONSES, "survey_id_question_id_hash_value", "");
        GSIUtils.addGSI(table, Tables.SURVEY_TEXT_QUESTION_AGGREGATED_RESPONSES, "survey_id", "question_id");
    }




}
