package com.mongodb.dao;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Updates.*;

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
        collection.find(and(gt("i", 50), lte("i", 100)))
                .forEach(getDocumentBlock());
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

    // 聚合操作
    public void aggregateOperation() {
        collection.aggregate(Arrays.asList(
                match(gt("i", 0)),
                project(Document.parse("{ITimes10: {$multiply: ['$i', 10]}}")))
        ).forEach(getDocumentBlock());
    }

    // 聚合操作分组求和
    public void aggregateGroupsAndSum() {
//        collection.aggregate(Arrays.asList(group(null, sum("total", "$i")))).forEach(getDocumentBlock());
        Document document = collection.aggregate(Collections.singletonList(group(null, sum("total", "$i")))).first();
        System.out.println(document.toJson());
    }

    // 最多更新一个Document
    public void singleUpdate() {
        collection.updateOne(eq("i", 10), set("i", 110));
    }

    // 更新多个Document
    public void multiUpdate() {
        UpdateResult updateResult = collection.updateMany(lt("i", 100), inc("i", 100));
        System.out.println("update document number: " + updateResult.getMatchedCount());
    }

    // 最多删除一个Document
    public void singleDelete() {
        collection.deleteOne(eq("i", 110));
    }

    // 删除多个Document
    public void multiDelete() {
        DeleteResult deleteResult = collection.deleteMany(gte("i", 110));
        System.out.println("delete document number: " + deleteResult.getDeletedCount());
    }

    // foreach方法要使用的用于打印查询结果的Block
    public Block<Document> getDocumentBlock() {
        Block<Document> printBlock = new Block<Document>() {
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        };

        return printBlock;
    }
}
