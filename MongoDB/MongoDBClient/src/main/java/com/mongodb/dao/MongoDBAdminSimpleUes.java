package com.mongodb.dao;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.TextSearchOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * Created by wei11 on 2017/5/15.
 * some of the administrative features available in the driver.
 */
public class MongoDBAdminSimpleUes {

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("test");
    MongoCollection<Document> collection = database.getCollection("firstTest");

    // 获取数据库信息
    public void getDatabases() {
        // 获取数据库名称
        for (String name: mongoClient.listDatabaseNames()) {
            System.out.println("数据库：" + name);
        }

        // 获取数据库详细信息
        for (Document document : mongoClient.listDatabases()) {
            System.out.println("数据库：" + document.get("name"));
            System.out.println(document.toJson());
        }
    }

    // 删除数据库
    public void dropDatabase(){
        mongoClient.getDatabase("test").drop();
    }

    // 新建Collection
    public void createCollection(){
        database.createCollection("cappedCollection",
                new CreateCollectionOptions().capped(true).sizeInBytes(0x100000));
    }

    // 获取Collection信息
    public void getCollections() {
        // 获取Collection名称
        for (String name: database.listCollectionNames()) {
            System.out.println("Collection: " + name);
        }

        // 获取Collection详细信息
        for (Document document : database.listCollections()) {
            System.out.println("Collection: " + document.get("name"));
            System.out.println(document.toJson());
        }
    }

    // 给字段创建索引
    public void createIndexForField() {
        collection.createIndex(Indexes.ascending("i"));
    }

    // 获取Collection的索引
    public void getCollectionIndexes(){
        for (final Document index : collection.listIndexes()) {
            System.out.println(index.toJson());
        }
    }

    // 创建文本索引
    public void createTextIndexes() {
//        collection.insertOne(new Document("_id", 0).append("content", "textual content"));
//        collection.insertOne(new Document("_id", 1).append("content", "additional content"));
//        collection.insertOne(new Document("_id", 2).append("content", "irrelevant content"));
//        collection.insertOne(new Document("_id", 3).append("content", "汉字内容"));

//        collection.createIndex(Indexes.text("content"));

        Block<Document> printBlock = new Block<Document>() {
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        };

        // Find using the text index
        long matchCount = collection.count(Filters.text("textual content -irrelevant"));
        System.out.println("Text search matches: " + matchCount);

        // Find using the $language operator
        Bson textSearch = Filters.text("textual content -irrelevant", new TextSearchOptions().language("english"));
        matchCount = collection.count(textSearch);
        System.out.println("Text search matches (english): " + matchCount);

        // Find the highest scoring match
        Document projection = new Document("score", new Document("$meta", "textScore"));
        System.out.println("Highest scoring document: ");
        collection.find(textSearch).projection(projection).forEach(printBlock);
    }

    // 执行命令
    public void runningCommand(){
        Document buildInfo = database.runCommand(new Document("buildInfo", 1));
        System.out.println(buildInfo);
    }


}
