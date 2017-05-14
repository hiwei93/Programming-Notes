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
myDoc = collection.find(exists("i")).sort(descending("i")).first();
System.out.println(myDoc.toJson());
```
- exists：选择具有指定字段的Document；
- descending：根据指定字段降序排序。

### 5. Projecting fields
有时候不需要Document中所有的数据，可以使用Projections类使projection parameter对应字段不显示。
``` java
myDoc = collection.find().projection(excludeId()).first();
System.out.println(myDoc.toJson());
```
- excludeId：不包含ID字段。