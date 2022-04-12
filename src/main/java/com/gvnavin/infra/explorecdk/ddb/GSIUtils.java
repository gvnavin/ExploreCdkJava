package com.gvnavin.infra.explorecdk.ddb;

import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.GlobalSecondaryIndexProps;
import software.amazon.awscdk.services.dynamodb.Table;

public class GSIUtils {
    public static void addGSI(Table tableSurveyResponse, String indexName, String pk, String sk) {
        GlobalSecondaryIndexProps.Builder builder = GlobalSecondaryIndexProps.builder()
                .indexName(indexName)
                .partitionKey(Attribute.builder()
                        .name(pk)
                        .type(AttributeType.STRING)
                        .build());

        if (!sk.isEmpty()) {
            builder.sortKey(Attribute.builder()
                    .name(sk)
                    .type(AttributeType.STRING)
                    .build());
        }

        tableSurveyResponse.addGlobalSecondaryIndex(builder.build());
    }
}
