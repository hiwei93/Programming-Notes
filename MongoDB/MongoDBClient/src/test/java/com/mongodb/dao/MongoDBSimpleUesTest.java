package com.mongodb.dao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wei11 on 2017/5/14.
 */
public class MongoDBSimpleUesTest {
    @Test
    public void singleDelete() throws Exception {
        MongoDBSimpleUes simpleUes = new MongoDBSimpleUes();
        simpleUes.singleDelete();
        simpleUes.allDocument();
    }

    @Test
    public void multiDelete() throws Exception {
        MongoDBSimpleUes simpleUes = new MongoDBSimpleUes();
        simpleUes.multiDelete();
        simpleUes.allDocument();
    }

    @Test
    public void singleUpdate() throws Exception {
        MongoDBSimpleUes simpleUes = new MongoDBSimpleUes();
        simpleUes.singleUpdate();
        simpleUes.allDocument();
    }

    @Test
    public void multiUpdate() throws Exception {
        MongoDBSimpleUes simpleUes = new MongoDBSimpleUes();
        simpleUes.multiUpdate();
        simpleUes.allDocument();
    }

    @Test
    public void aggregateGroupsAndSum() throws Exception {
        new MongoDBSimpleUes().aggregateGroupsAndSum();
    }

    @Test
    public void aggregateOperation() throws Exception {
        new MongoDBSimpleUes().aggregateOperation();
    }

    @Test
    public void projectingFields() throws Exception {
        new MongoDBSimpleUes().projectingFields();
    }

    @Test
    public void sortDocuments() throws Exception {
        new MongoDBSimpleUes().sortDocuments();
    }

    @Test
    public void getFirstDocumentByFilter() throws Exception {
        new MongoDBSimpleUes().getFirstDocumentByFilter();
    }

    @Test
    public void getAllDocumentsByFilter() throws Exception {
        new MongoDBSimpleUes().getAllDocumentsByFilter();
    }

    @Test
    public void allDocument() throws Exception {
        new MongoDBSimpleUes().allDocument();
    }

    @Test
    public void firstDocument() throws Exception {
        new MongoDBSimpleUes().firstDocument();
    }

    @Test
    public void documentNum() throws Exception {
        System.out.println("This collection have " +
                new MongoDBSimpleUes().documentNum() +
                " documents");
    }

    @Test
    public void multiInsert() throws Exception {
        new MongoDBSimpleUes().multiInsert();
    }

    @Test
    public void singleInsert() throws Exception {
        new MongoDBSimpleUes().singleInsert();
    }

}