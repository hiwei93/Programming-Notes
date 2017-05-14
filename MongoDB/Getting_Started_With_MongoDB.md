# [Getting Started With MongoDB](https://docs.mongodb.com/getting-started/shell/)
> [MongoDB官网](https://www.mongodb.com/)
> [MongoDB官网教程](https://docs.mongodb.com/manual/)
> [中文教程](http://docs.mongoing.com/manual-zh/index.html)

## 一、介绍
### 1. mongoDB简介
MongoDB是一个开源的文档数据库，具有高性能、高可用性和自动缩放的特性。MongoDB不需要ORM来促进开发。

### 2. 文档数据库
MongoDB中的一个记录就是一个文档，文档是由字段和值组成的数据结构。MongoDB文档与JSON对象相似。字段的值可能包含其他的文档、数组和文档数组。
``` javascript
{
   "_id" : ObjectId("54c955492b7c8eb21818bd09"),
   "address" : {
      "street" : "2 Avenue",
      "zipcode" : "10075",
      "building" : "1480",
      "coord" : [ -73.9557413, 40.7720266 ]
   },
   "borough" : "Manhattan",
   "cuisine" : "Italian",
   "grades" : [
      {
         "date" : ISODate("2014-10-01T00:00:00Z"),
         "grade" : "A",
         "score" : 11
      },
      {
         "date" : ISODate("2014-01-16T00:00:00Z"),
         "grade" : "B",
         "score" : 17
      }
   ],
   "name" : "Vella",
   "restaurant_id" : "41704620"
}
```

### 3. 集合
1. MongoDB将文档储存在集合中。集合与关系型数据库中的表相似。但是，与表不同，一个集合中的文档可以具有不同的模式。
2. MongoDB中，储存在集合中的文档必须具有一个唯一的`_id`字段作为主键。

## 二、导入数据集
进入命令提示符，打开MongoDB的服务（或者执行mongod）
使用mongoimport导入数据集：
```
mongoimport --db test --collection restaurants --drop --file ~/downloads/primer-dataset.json
```
- --db：选择数据库；
- --collection：选择集合；
- --drop：当集合已经存在，使用该参数则会删除之前的集合，重新创建集合
- --file：导入数据文件所在地址；
> 还可以通过`--host`和`--port`指定不同的主机和端口。

## 三、MongoDB Shell (mongo)
mongo shell是一个互动的JavaScript界面，是MongoDB包的组件。可以使用mongo shell来查询和更新数据以及进行管理操作。

**启动mongo**
使用`mongo`指令，或是运行`mongo.exe`
> 在运行mongo shell前先启动MongoDB Service（或是运行mongod.exe）。
> 使用`help`指令，查看刚铎操作数据库的指令。

## 四、CRUD操作
首先需要选择数据库：
``` powershell
use dbName
```

### 1. 插入数据
``` javascript
db.collectionName.insert({... ...})
```
1. 如果添加文档数据所在的collection不存在，MongoDB会创建一个collection。
2. 该方法会返回WriteResult对象，该对象包含一个 操作状态。
3. 如果insert()方法传入的文档中不包含_id字段，则该字段自动添加到文档中，该字段的值想设置为生成的ObjectId。

### 2. 查询数据
``` javascript
db.collectionName.find()
```
1. 返回collection中所有的document；
2. 可以在文档中指定过滤器或者条件，并作为参数传递给find()方法；
3. 该方法在右表中返回查询结果，该有表是文档生成的可迭代对象。

#### 1. 指定等式条件
字段上的等式匹配的查询条件具有以下形式：
``` javascript
{ <field1>: <value1>, <field2>: <value2>, ... }
```
- 如果`<field>`是顶级字段，而不是嵌入式文档或数组中的字段，则可以使用引号括起字段名称或省略引号。
- 如果`<field>`在嵌入式文档或数组中，则使用点符号来访问该字段。使用[点表示法](https://docs.mongodb.com/manual/core/document/#dot-notation)，您必须用引号将点符号名称标识出来。

1. 通过顶级字段查找
``` javascript
db.restaurants.find( { "borough": "Manhattan" } )
```
将查询`borough`字段值为`Manhattan`的document

2. 通过嵌入式文档中的字段查找
需要使用点表示法，点表示法需要用引号将整个点符号名字段标识出来：
``` javascript
db.restaurants.find( { "address.zipcode": "10075" } )
```

3. 通过数组中的字段查找
需要使用点表示法
``` javascript
db.restaurants.find( { "grades.grade": "B" } )
```

#### 2. 使用运算符指定条件
MongoDB提供运算符指定查询条件，比如比较运算符。
除了`$or`和`$and`等运算符外，使用运算符查询条件一般有以下形式：
``` javascript
{ <field1>: { <operator1>: <value1> } }
```
[运算符列表](http://docs.mongodb.com/manual/reference/operator/query)

1. 大于运算符（$gt）
``` javascript
db.restaurants.find( { "grades.score": { $gt: 30 } } )
```

2. 小于运算符（$lt）
``` javascript
db.restaurants.find( { "grades.score": { $lt: 10 } } )
```

#### 3. 组合条件
使用逻辑与（AND）和逻辑或（OR）中组合多个查询条件
1. 逻辑与
使用`,`分隔查询条件指定逻辑与查询：
``` javascript
db.restaurants.find( { "cuisine": "Italian", "address.zipcode": "10075" } )
```

2. 逻辑或
使用`$or`运算符指定逻辑或查询：
``` javascript
db.restaurants.find(
   { $or: [ { "cuisine": "Italian" }, { "address.zipcode": "10075" } ] }
)
```

#### 4. 查询结果排序
将sort()方法附加到查询方法后，给sort()方法传递一个包含要排序字段和其排序类型的document，如1为升序，-1位降序。
``` javascript
db.restaurants.find().sort( { "borough": 1, "address.zipcode": -1 } )
```
结果集中首先按照`borough`字段升序排序，然后在每个`borough`内，按照`address.zipcode`升序排序。

### 3. 更新数据
使用`update()`方法更新数据，该方法接受的参数有：
1. 匹配文档进行更新操作的过滤器document；
2. 用来指定要执行修改的更新document；
3. 选项参数（可选）

- 更新操作过滤器的结构和语法与查询条件的相同；
- update()方法默认更新单个document，可以使用`multi`选项更新所有匹配的document；
- 不能更新`_id字段`;
- 指定upsert选项为true，则如果没有匹配的document就会插入一个新文档；
- 更新操作将返回一个带有操作状态的WriteResult对象。

#### 1. 更新指定字段
- MongoDB提供了更新操作符用于更新操作，比如使用`$set`修改值。
- 一些更新操作符，如果更新字段不存在则会创建该字段，如`$set`。

1. 更新顶级字段
``` javascript
db.restaurants.update(
    { "name" : "Juni" },
    {
      $set: { "cuisine": "American (New)" },
      $currentDate: { "lastModified": true }
    }
)
```
- 将会更新name为"Juni"的第一个document；
- `$set`操作符将更新cuisine字段；
- `$currentDate`操作符将lastModified字段的值为当前日期。

2. 更新嵌入式字段
需要使用点符号：
``` javascript
db.restaurants.update(
  { "restaurant_id" : "41156888" },
  { $set: { "address.street": "East 31st Street" } }
)
```

3. 更新多个document
``` java
db.restaurants.update(
  { "address.zipcode": "10016", cuisine: "Other" },
  {
    $set: { cuisine: "Category To Be Determined" },
    $currentDate: { "lastModified": true }
  },
  { multi: true}
)
```

#### 2. 替换Document
- 将一个新的document作为`update()`方法的第二个参数即可完全替换之前的document；
- 新的document可以与原来的document具有不一样的字段；
- 不能更改`_id`字段，因为该字段是不可变的。

``` javascript
db.restaurants.update(
   { "restaurant_id" : "41704620" },
   {
     "name" : "Vella 2",
     "address" : {
              "coord" : [ -73.9557413, 40.7720266 ],
              "building" : "1480",
              "street" : "2 Avenue",
              "zipcode" : "10075"
     }
   }
)
```

### 4. 删除数据
通过给`remove()`方法指定条件document来删除document，指定删除条件的结构和语法与查询条件相同。
`remove()`方法的返回值是一个带有操作状态的WriteResult对象。其中`nRemoved`字段为删除document的数量。

1. 删除所有符合条件的document
``` javascript
db.restaurants.remove( { "borough": "Manhattan" } )
```

2. 使用`justOne`选项
``` javascript
db.restaurants.remove( { "borough": "Queens" }, { justOne: true } )
```
justOne选项：将删除操作限制为只有一个匹配的文档。

3. 移除所有的document
``` javascript
db.restaurants.remove( { } )
```

4. 删除集合
删除集合中的所有文档，删除整个集合（包括索引）可能会更有效，然后重新创建一个集合并重建索引。
``` javascript
db.restaurants.drop()
```
如果集合删除成功，将返回true；如果集合不存在，将返回false。

## 五、数据聚合
1. MongoDB可以进行聚合操作，像通过只是那份的键进行分组，并为每个不同组评估总计或计数。

2. 使用`aggregate()`方法进行基于阶段的聚合。`aggregate()`方法接受一个阶段数组，其中顺序处理的每个阶段描述了一个数据处理步骤。
``` javascript
db.collection.aggregate( [ <stage1>, <stage2>, ... ] )
```

### 1. 通过字段分组并计数
使用`$group` stage按指定的键进行分组。
- 在`$group` stage中，通过`_id`字段指定分组的键；
- `$group` 通过字段路径访问字段，字段路径是以美元符`$`作为前缀的字段名称。
- `$group` stage 可以使用累加器为每组进行计算，比如`$sum`累加器。

``` javascript
db.restaurants.aggregate(
   [
     { $group: { "_id": "$borough", "count": { $sum: 1 } } }
   ]
);
```
结果集为：
``` javascript
{ "_id" : "Staten Island", "count" : 969 }
{ "_id" : "Brooklyn", "count" : 6086 }
{ "_id" : "Manhattan", "count" : 10259 }
{ "_id" : "Queens", "count" : 5656 }
{ "_id" : "Bronx", "count" : 2338 }
{ "_id" : "Missing", "count" : 51 }
```

### 2. 过滤器和分组文件
使用`$match` stage过滤文件，使用的是查询语法。
``` javascript
db.restaurants.aggregate(
   [
     { $match: { "borough": "Queens", "cuisine": "Brazilian" } },
     { $group: { "_id": "$address.zipcode" , "count": { $sum: 2} } }
   ]
);
```
结果集为：
``` java
{ "_id" : "11368", "count" : 1 }
{ "_id" : "11106", "count" : 3 }
{ "_id" : "11377", "count" : 1 }
{ "_id" : "11103", "count" : 1 }
{ "_id" : "11101", "count" : 2 }
```

## 六、索引
1. 索引可以使查询变得高效。在没有索引的情况下，MongoDB必须扫描集合中全部的document，来选择与查询语句相匹配的document。如果查询存在适当的索引，MongoDB可以使用该索引来限制它必须检查的文档数量。

2. 使用`createIndex()`方法在一个collection创建索引。

3. 创建集合后，MongoDB会自动在`_id`字段上创建一个索引。

4. 在字段上创建索引，给`createIndex()`方法传递一个索引键指定文档，这个文档列出了要索引的字段和每个字段的索引类型：
``` javascript
{ <field1>: <type1>, ...}
```
> 升序索引，指定`<type1>`为1；
> 降序索引，指定`<type1>`为-1；

### 1. 创建单一字段索引
``` javascript
db.restaurants.createIndex( { "cuisine": 1 } )
```
返回带有操作状态的文档：
``` javascript
{
  "createdCollectionAutomatically" : false,
  "numIndexesBefore" : 1,
  "numIndexesAfter" : 2,
  "ok" : 1
}
```
索引创建成功后，"numIndexesAfter"的值要大于"numIndexesBefore"的值。

### 2. 创建复合索引
MongoDB支持复合索引，即多字段索引。字段的顺序决定索引如何储存索引的键。
``` javascript
db.restaurants.createIndex( { "cuisine": 1, "address.zipcode": -1 } )
```
返回带有操作状态的文档：
``` javascript
{
   "createdCollectionAutomatically" : false,
   "numIndexesBefore" : 2,
   "numIndexesAfter" : 3,
   "ok" : 1
}
```
索引创建成功后，"numIndexesAfter"的值要大于"numIndexesBefore"的值。