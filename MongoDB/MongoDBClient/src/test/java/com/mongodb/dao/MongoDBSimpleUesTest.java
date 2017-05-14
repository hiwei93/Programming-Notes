package com.mongodb.dao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wei11 on 2017/5/14.
 */
public class MongoDBSimpleUesTest {
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