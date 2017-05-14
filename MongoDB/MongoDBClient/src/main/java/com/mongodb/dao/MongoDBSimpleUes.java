package com.mongodb.dao;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;

/**
 * Created by wei11 on 2017/5/14.
 */
public class MongoDBSimpleUes {
    // 获取连接
    MongoClient mongoClient = new MongoClient("localhost", 27017);

    MongoDatabase database = mongoClient.getDatabase("test");

    // 获取指定的collection
    MongoCollection<Document> collection = database.getCollection("firstTest");

    // 插入单个数据
    public void singleInsert() {
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new Document("x", 203).append("y", 102));

        collection.insertOne(doc);
    }

    // 插入多个数据
    public void multiInsert() {
        List<Document> documents = new ArrayList<Document>();
        for (int i = 0; i < 100; i++) {
            documents.add(new Document("i", i));
        }

        collection.insertMany(documents);
    }

    //统计Document的个数
    public long documentNum() {
        return collection.count();
    }

    // 查询第一个Document
    public void firstDocument() {
        Document document = collection.find().first();
        System.out.println("The first document is \n" + document.toJson());
    }

    // 获取全部的Document
    public void allDocument() {
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }

    // 通过查询过滤器获得一个Document
    public void getFirstDocumentByFilter(){
        Document document = collection.find(eq("i", 71)).first();
        System.out.println(document.toJson());
    }

    // 通过查询过滤器获得Document集合
    public void getAllDocumentsByFilter() {
        Block<Document> printBlock = new Block<Document>() {
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        };

        collection.find(and(gt("i", 50), lte("i", 100))).forEach(printBlock);
    }

    // Document排序
    public void sortDocuments(){
        Document document = collection.find(exists("i")).sort(descending("i")).first();
        System.out.println(document.toJson());
    }

    // Projecting fields
    public void projectingFields(){
        Document document = collection.find().projection(exclude("_id")).first();
        System.out.println(document.toJson());
    }
}
