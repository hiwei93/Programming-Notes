# MongoDB Java Driver
> [官方文档](http://mongodb.github.io/mongo-java-driver/3.2/driver/)

## 1. 导入驱动
### 1. MongoDB Driver
MongoDB Drivers是同步更新的Java驱动，该驱动包含原来的API以及新的通用的MongoCollection接口，该接口符合新的交叉驱动的CRUD规范。
``` xml
<dependencies>
    <dependency>
        <groupId>org.mongodb</groupId>
        <artifactId>mongodb-driver</artifactId>
        <version>3.2.2</version>
    </dependency>
</dependencies>
```
还需要以下依赖：bson和mongodb-drive-core。

### 2. Uber MongoDB Java Driver
uber jar包含BSON库，核心库和mongodb-drive。
``` xml
<dependencies>
    <dependency>
        <groupId>org.mongodb</groupId>
        <artifactId>mongo-java-driver</artifactId>
        <version>3.2.2</version>
    </dependency>
</dependencies>
```

## 2. 连接数据库
### 1. 获取MongoClient 实例
有五种方式
``` java
// To directly connect to a single MongoDB server
// (this will not auto-discover the primary even if it's a member of a replica set)
MongoClient mongoClient = new MongoClient();

// or
MongoClient mongoClient = new MongoClient( "localhost" );

// or
MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
```
前三种方式不会自动发现主数据库，即使所连接的数据库是副本成员。

``` java
// or, to connect to a replica set, with auto-discovery of the primary, supply a seed list of members
MongoClient mongoClient = new MongoClient(
  Arrays.asList(new ServerAddress("localhost", 27017),
                new ServerAddress("localhost", 27018),
                new ServerAddress("localhost", 27019)));

// or use a connection string
MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017,localhost:27018,localhost:27019");
MongoClient mongoClient = new MongoClient(connectionString);
```
后两种方式会自动发现主数据库，根据提供的部分成员列表连接副本集。
> MongoClient实例相当于一个数据库连接池，即使进行多线程编程也只需要一个MongoClient实例。
>  如果创建多个MongoClient实例，则
>  - 需要为每个实例医用资源使用限制（比如最大连接数等）；
>  - 处理一个实例时区表使用MongoClient.close()清理资源。

### 2. 连接指定数据库
如果数据库不存在则会创建该数据库。
``` java
MongoDatabase database = mongoClient.getDatabase("Database Name");
```

## 3. 获取Collection
``` java
MongoCollection<Document> collection = database.getCollection("Collection Name");
```

## 4. 操作Collection
### 1. 插入Document
#### 1. 创建Document对象
``` java
Document doc = new Document("name", "MongoDB")
               .append("type", "database")
               .append("count", 1)
               .append("info", new Document("x", 203).append("y", 102));
```
对应的json格式为：
``` json
{
   "name" : "MongoDB",
   "type" : "database",
   "count" : 1,
   "info" : {
               x : 203,
               y : 102
             }
}
```

#### 2. 插入单个Document
``` java
collection.insertOne(doc);
```

#### 3. 插入多个Document
insertMany()方法：
``` java
List<Document> documents = new ArrayList<Document>();
collection.insertMany(documents);
```

### 2. 统计Collection中Document个数
``` java
collection.count()
```

### 3. 查询Collection
#### 1. 查询Collection的第一个Document
``` java
Document myDoc = collection.find().first();
System.out.println(myDoc.toJson());
```

#### 2. 查询Collection的全部Document
``` java
MongoCursor<Document> cursor = collection.find().iterator();
try {
    while (cursor.hasNext()) {
        System.out.println(cursor.next().toJson());
    }
} finally {
    cursor.close();
}
```
- `find()`方法会返回一个`FindIterable`实例；
- `FindIterable`实例为连接或控制查找操作提供流畅的接口；
- 使用`iterator()`方法获得一个查询结果的迭代对象；

**不鼓励**下列所示的foreach迭代方式：
``` java
for (Document cur : collection.find()) {
    System.out.println(cur.toJson());
}
```

#### 3. 通过查询过滤器获得一个Document
``` java
import static com.mongodb.client.model.Filters.*;

myDoc = collection.find(eq("i", 71)).first();
System.out.println(myDoc.toJson());
```

#### 4. 通过查询过滤器获得Document集合
```
// now use a range query to get a larger subset
Block<Document> printBlock = new Block<Document>() {
     @Override
     public void apply(final Document document) {
         System.out.println(document.toJson());
     }
};
collection.find(gt("i", 50)).forEach(printBlock);
```
使用`FindIterable`的`forEach()`方法，可以对每个Document使用Block，打印符合条件的Document。

也可以查询一个范围内的Document：
``` java
collection.find(and(gt("i", 50), lte("i", 100))).forEach(printBlock);
```

### 4. Document排序
``` java
import static com.mongodb.client.model.Sorts.*;

myDoc = collection.find(exists("i")).sort(descending("i")).first();
System.out.println(myDoc.toJson());
```
- exists：选择具有指定字段的Document；
- descending：根据指定字段降序排序。

### 5. Projecting fields
有时候不需要Document中所有的数据，可以使用Projections类使projection parameter对应字段不显示。
``` java
import static com.mongodb.client.model.Projections.*;

myDoc = collection.find().projection(excludeId()).first();
System.out.println(myDoc.toJson());
```
- excludeId：不包含ID字段。

### 6. 聚合
Aggregates helper为每种类型的聚合阶段提供了构建器。
下面的例子用来计算i*10的值:
``` java
collection.aggregate(Arrays.asList(
        match(gt("i", 0)),
        project(Document.parse("{ITimes10: {$multiply: ['$i', 10]}}")))
).forEach(printBlock);
```
- Aggregates.match()：匹配i大于0的document；
-  $multiply ：将两数相乘，并返回结果数组。

$group操作使用Accumulators helper进行任何累积操作，比如累加操作：
``` java
myDoc = collection.aggregate(Collections.singletonList(group(null, sum("total", "$i")))).first();
System.out.println(myDoc.toJson());
```

### 7. 更新Document
#### 1. 最多更新一个Document
``` java
collection.updateOne(eq("i", 10), set("i", 110));
```

#### 2. 更新多个Document
``` java
UpdateResult updateResult = collection.updateMany(lt("i", 100), inc("i", 100));
System.out.println(updateResult.getModifiedCount());
```
- Updates.inc：未指定字段的值增加固定的值；
- UpdateResult：提供操作的相关信息，包括更新Document的数量。

### 8. 删除Document
#### 1. 最多删除一个Document
``` java
collection.deleteOne(eq("i", 110));
```
#### 2. 删除多个Document
``` java
DeleteResult deleteResult = collection.deleteMany(gte("i", 100));
System.out.println(deleteResult.getDeletedCount());
```
- DeleteResult：提供操作的相关信息，包括删除Document的数量。

### 9. 批量操作（Bulk operations）
新命令允许执行批量插入/更新/删除操作。
批量操作有两种类型：
1. 有序批量操作
循序执行所有操作，首次出现写入错误时输出错误；
``` java
// 1. Ordered bulk operation - order is guarenteed
collection.bulkWrite(
  Arrays.asList(new InsertOneModel<>(new Document("_id", 4)),
                new InsertOneModel<>(new Document("_id", 5)),
                new InsertOneModel<>(new Document("_id", 6)),
                new UpdateOneModel<>(new Document("_id", 1),
                                     new Document("$set", new Document("x", 2))),
                new DeleteOneModel<>(new Document("_id", 2)),
                new ReplaceOneModel<>(new Document("_id", 3),
                                      new Document("_id", 3).append("x", 4))));
```

2. 无序批量操作
执行所有操作并报告任何错误。
``` java
 // 2. Unordered bulk operation - no guarantee of order of operation
collection.bulkWrite(
  Arrays.asList(new InsertOneModel<>(new Document("_id", 4)),
                new InsertOneModel<>(new Document("_id", 5)),
                new InsertOneModel<>(new Document("_id", 6)),
                new UpdateOneModel<>(new Document("_id", 1),
                                     new Document("$set", new Document("x", 2))),
                new DeleteOneModel<>(new Document("_id", 2)),
                new ReplaceOneModel<>(new Document("_id", 3),
                                      new Document("_id", 3).append("x", 4))),
  new BulkWriteOptions().ordered(false));
```

> 对于使用2.6以前的版本，不建议使用`bulkWrite`方法，因为这是首个版本，该版本允许驱动为BulkWriteResult和BulkWriteException实现正确语义的方式来支持增删改批量写入的命令。这些方法仍然适用于2.6之前的服务器，但因为每次写操作必须一次执行一次，性能将受到影响。