package com.mongodb.dao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wei11 on 2017/5/15.
 */
public class MongoDBAdminSimpleUesTest {
    @Test
    public void runningCommand() throws Exception {
        new MongoDBAdminSimpleUes().runningCommand();
    }

    @Test
    public void createTextIndexes() throws Exception {
        new MongoDBAdminSimpleUes().createTextIndexes();
    }

    @Test
    public void createIndexForField() throws Exception {
        MongoDBAdminSimpleUes adminSimpleUes = new MongoDBAdminSimpleUes();
        adminSimpleUes.createIndexForField();
        adminSimpleUes.getCollectionIndexes();
    }

    @Test
    public void getCollectionIndexes() throws Exception {
        new MongoDBAdminSimpleUes().getCollectionIndexes();
    }

    @Test
    public void dropDatabase() throws Exception {
        MongoDBAdminSimpleUes adminSimpleUes = new MongoDBAdminSimpleUes();
        adminSimpleUes.dropDatabase();
        adminSimpleUes.getDatabases();
    }

    @Test
    public void createCollection() throws Exception {
        MongoDBAdminSimpleUes adminSimpleUes = new MongoDBAdminSimpleUes();
        adminSimpleUes.createCollection();
        adminSimpleUes.getCollections();
    }

    @Test
    public void getCollections() throws Exception {
        new MongoDBAdminSimpleUes().getCollections();
    }

    @Test
    public void getDatabases() throws Exception {
        new MongoDBAdminSimpleUes().getDatabases();
    }

}