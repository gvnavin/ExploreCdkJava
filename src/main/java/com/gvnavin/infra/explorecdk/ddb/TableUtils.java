package com.gvnavin.infra.explorecdk.ddb;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.Table;

public class TableUtils {

    public static Table createTable(Stack stack, String tableName, String pk, String sk) {
        Table.Builder builder = Table.Builder.create(stack, tableName)
                .tableName(tableName)
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

        Table table = builder.build();
        return table;
    }

}
