package edu.byu.cs.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class AuthDAO implements AuthInterface{

    public Table connect() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion("us-west-2")
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("authtoken");

        return table;
    }

    @Override
    public void addToken(String token, String timeStamp) {
        Table table = connect();

        Item item = new Item()
                .withPrimaryKey("token", token)
                .withString("timestamp", timeStamp);
        table.putItem(item);
    }

    @Override
    public String validateToken(String token) {
        Table table = connect();

        Item item = table.getItem("token", token);
        if (item == null) {
            return null;
        }
        return item.getString("timestamp");
    }

}
