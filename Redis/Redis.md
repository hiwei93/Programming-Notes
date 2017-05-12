# Redis
> [官网](https://redis.io/)
> [中文官网](http://www.redis.cn/)
> [中文教程](http://www.redis.net.cn/)
> [Redis Cheat Sheet](http://www.cheatography.com/tasjaevan/cheat-sheets/redis/)
> [Try Redis](http://try.redis.io/)

## 一、认识Redis
Redis是一个开源（BSD许可），内存存储的数据结构服务器，可用作数据库，高速缓存和消息队列代理。

## 二、Redis数据类型
### 1. String
- string是redis最基本的类型，一个key对应一个value；
- string类型是二进制安全的，即redis的string可以包含任何数据（比如jpg图片或者序列化的对象 ）；
- 一个键最大能存储512MB。
```
redis 127.0.0.1:6379> SET key "value"
OK
redis 127.0.0.1:6379> GET key
"value"
```

### 2. Hash
- Redis hash 是一个键值对集合；
- Redis hash是一个string类型的field和value的映射表；
- hash特别适合用于存储对象;
- 每个 hash 可以存储 $2^{32 - 1}$键值对（40多亿）。
```
redis 127.0.0.1:6379> HMSET user:1 username admin password admin age 20
OK
redis 127.0.0.1:6379> HGETALL user:1

1) "username"
2) "admin"
3) "password"
4) "admin"
5) "age"
6) "20"
```

### 3. List
- Redis 列表是简单的字符串列表，按照插入顺序排序（有序String列表）；
- 可以添加一个元素到列表的头部（左边LPUSH）或者尾部（右边RPUSH）;
- 列表最多可存储$2^{32 - 1}$个元素。
``` 
redis 127.0.0.1:6379> lpush key value1
(integer) 1
redis 127.0.0.1:6379> lpush key value2 value3
(integer) 3
redis 127.0.0.1:6379> rpush key value4
(integer) 4
redis 127.0.0.1:6379> lrange key 0 4

1) "value3"
2) "value2"
3) "value1"
4) "value4"
```
> `lpush`在列表头（左边）部插入数据，`rpush`在列表尾（右边）部插入数据；
> `LRANGE key start end`：start与end可以接受负数，如-1就是列表最后一个元素，-2就是列表倒数第二个元素。

### 4. Set
- Redis的Set是string类型的无序集合，不允许成员重复。
- 集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)；
- 集合中最大的成员数为$2^{32 - 1}$；
```
redis 127.0.0.1:6379> sadd key member1
(integer) 1
redis 127.0.0.1:6379> sadd key member2 member3
(integer) 2
redis 127.0.0.1:6379> sadd key member1
(integer) 0
redis 127.0.0.1:6379> smembers key

1) "member1"
2) "member2"
3) "member3"
```
> `sadd`：添加一个string成员到key对应的set集合中
> - 成功返回1；
> - 如果该成员已存在集合中返回0。

### 5. ZSet（sorted set）
- Redis zset 和 set 一样也是string类型元素的集合，且成员不允许重复；
- 不同的是zset中每个元素都会关联一个double类型的分数，redis通过分数来为集合中的成员进行从小到大的排序；
- zset的成员是唯一的，但分数(score)却可以重复。
```
redis 127.0.0.1:6379> zadd key 0 member1
(integer) 1
redis 127.0.0.1:6379> zadd key 0 member2 0 member3
(integer) 2
redis 127.0.0.1:6379> zadd key 1 member3
(integer) 0
redis 127.0.0.1:6379> ZRANGEBYSCORE key 0 1 LIMIT 0 4 WITHSCORES

1) "member1"
2) 0.0
3) "member2"
4) 0.0
5) "member3"
6) 1.0
```
> 向有序集合添加成员的时候，如果成员已经存在，则会改变成员的score，如例子中的member3，第二次添加的时候，将其score从0改为1；
> 返回有序集合所有成员的命令，根据redis的版本不同有两种：
> 1. `ZRANGEBYSCORE key min max [LIMIT offset count] (Redis >= 1.1)`；
> 2. `ZRANGEBYSCORE key min max [LIMIT offset count] [WITHSCORES] (Redis >= 1.3.4)`；
> - LIMIT offset count：指定最多现实的数据，从offsert开始count个；
> - WITHSCORES：将成员的score也显示出来。

# Jedis
> [The jedis wiki](https://github.com/xetorthio/jedis/wiki/Getting-started)

## 1. 导入Jedis
1. 使用jar包
下载[jedis的jar包](http://search.maven.org/#search%7Cgav%7C1%7Cg:%22redis.clients%22%20AND%20a:%22jedis%22)和[Apache共享池](http://search.maven.org/#artifactdetails%7Corg.apache.commons%7Ccommons-pool2%7C2.0%7Cjar)

2. 使用Maven配置依赖
``` xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.9.0</version>
    <type>jar</type>
    <scope>compile</scope>
</dependency>
```

## 2. 基本使用
### 1. 直接获取Jedis实例
``` java
Jedis jedis = new Jedis("localhost", 6379);
jedis.set("key", "value");
String value = jedis.get("key");
```
1. 多线程环境下不赞成这样使用，不同的线程使用同样的实例或出现奇怪的异常；
2. 创建多个Jedis实例意味着有多个socket和连接，同样会导致奇怪的异常；
3. 单个Jedis实例不是线程安全的（不能用单例模式）。
> 综上，建议使用JedisPool。

### 2. 使用JedisPool
JedisPool是线程安全的网络连接池。
可以使用JedisPool可靠的创建多个Jedis实例。
1. 初始化一个连接池
``` java
JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
```
- 可以静态化JedisPool，它是线程安全的。
- JedisPoolConfig：包含了一系列有用的Redis特定的连接池默认值；
- JedisPool 基于 Commons Pool 2，跟多内容查看[Commons Pool 2官方文档](http://commons.apache.org/proper/commons-pool/apidocs/org/apache/commons/pool2/impl/GenericObjectPoolConfig.html)

2. 使用连接池创建Jedis实例
``` java
/// Jedis implements Closeable. Hence, the jedis instance will be auto-closed after the last statement.
try (Jedis jedis = pool.getResource()) {
  /// ... do stuff here ... for example
  jedis.set("foo", "bar");
  String foobar = jedis.get("foo");
  jedis.zadd("sose", 0, "car"); jedis.zadd("sose", 0, "bike"); 
  Set<String> sose = jedis.zrange("sose", 0, -1);
}
/// ... when closing your application:
pool.destroy();
```
> Jedis实现了Closeable，Jedis实例会在最后一个语句后自动关闭。

上例使用了`try-with-resource`，如果无法使用该语法，需要使用Jedis.close()关闭Jedis
``` java
Jedis jedis = null;
try {
  jedis = pool.getResource();
  /// ... do stuff here ... for example
  jedis.set("foo", "bar");
  String foobar = jedis.get("foo");
  jedis.zadd("sose", 0, "car"); jedis.zadd("sose", 0, "bike"); 
  Set<String> sose = jedis.zrange("sose", 0, -1);
} finally {
  if (jedis != null) {
    jedis.close();
  }
}
/// ... when closing your application:
pool.destroy();
```
> `try-with-resources`语句可以自动调用资源的close()函数。
> 可以看到与普通的`try-catch`方法不同，`try-with-resources`在`try`后有一个括号，初始化对象在括号内进行。
> 更多内容参看[官方文档：The try-with-resources Statement](http://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html)