# 一、JDBC
主要的类：
- DriverManager
- Connection 
- Statement 
- ResultSet 
- SQLException

## 1. 导入对引发数据库的JDBC包
## 2. 注册JDBC驱动
``` java
Class.forName("com.mysql.jdbc.Driver");
```
## 3. 连接数据库
DriverManager类开启一个连接
``` java
Connection conn = DriverManager.getConnection(url,username,password);
```
## 4. 执行SQL语句
1. Connection类创建一个Statement类
``` java
Statement stmt = conn.createStatement();
```

2. 编写SQL语句

3. Statement的executeQuery()方法执行SQL语句
``` java
stmt.executeQuery(sql);
```

## 5. 接受返回结果
ResultSet类接受SQL执行返回结果
``` java
ResultSet rs = stmt.executeQuery(sql);
while(rs.next()){ ... ... }
```

## 6. 关闭连接
``` java
ResultSet.close();
Statement.close();
Connection.close();
```

## 7. 事务
1. 关闭自动提交
``` java
Connection.setAutoCommit(false);
```
2. 完成相关数据操作后，手动提交
``` java
Connection.commit();
```
3. commit报错后可在catch块儿中进行回滚
``` java
Connection.rollback();
```

# 二、Hibernate
> [官方文档](http://hibernate.org/orm/documentation/5.2/)

**主要的类：**
- SessionFactory 
- Session
- Transaction 
- Query /SQLQuery

**主要的配置文件：**
- hibernate.cfg.xml：主配置文件
- Entity.hbm.xml：映射文件

## 1. 配置数据库连接信息
hibernate.cfg.xml
``` xml
<hibernate-configuration>
<session-factory>
	<!-- 数据库方言 -->
	<property name="hibernate.dialect">
		org.hibernate.dialect.MySQLDialect
	</property>
	<!-- 驱动类 -->
	<property name="hibernate.connection.driver_class">
		com.mysql.jdbc.Driver
	</property>
	<property name="hibernate.connection.username">root</property>
	<property name="hibernate.connection.password">root</property>
	<property name="hibernate.connection.url">
		<![CDATA[
        		jdbc:mysql://localhost:3306/imooc?useUnicode=true&characterEncoding=utf8
        	]]>
	</property>
</session-factory>
</hibernate-configuration>
```

## 2. 创建持久化类 （POJO类）

## 3. 配置映射文件
将数据库和POJO类一一对应
``` xml
<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC 
	"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
	"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
	<class name="com.entity.XX" table="XX">
		<!-- 主键 -->
		<id name="" column="" type="">
			<generator class="native"></generator>
		</id>
		
		<property name="" type="">
			<column name="" length="" not-null=""></column>
		</property>
		<!-- 或者 -->
		<property name="firstName" column="first_name" type="string"/>
	</class>
</hibernate-mapping>
```

1. 一对多映射
``` xml
    <set name="" cascade="delete" inverse="true">
		<key column=""></key>
		<one-to-many class=""/>
	</set>
```
- cascadee：外键的级联关系；
- inverse：true放弃一对多的外键维护能力。

2. 多对一
``` xml
<many-to-one name="" class="" column=""></many-to-one>
```

## 4. 将映射文件配置到主配置文件
hibernate.cfg.xml
``` xml
<mapping resource="com/entity/Entity.hbm.xml" />
```

## 5. 创建SessionFactory
``` java 
	Configuration config = new Configuration().configure();
	// A SessionFactory is set up once for an application!
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure() // configures settings from hibernate.cfg.xml
			.build();
	try {
		SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
	}
	catch (Exception e) {
		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
		// so destroy it manually.
		StandardServiceRegistryBuilder.destroy( registry );
	}
}
```

## 6. 打开会话
``` java
Session session = sessionFactory.openSession();
```

## 7. 开启事务
``` java
Transaction tx = session.beginTransaction();
```

## 8. 数据操作
``` java
session.get();
session.save();
session.update();
session.delete();
```

## 9. 提交事务
``` java
Transaction.commit();
```

## 10. 关闭会话
``` java
session.close();
```

> 执行操作的完整例子：
``` java
Session session = factory.openSession();
Transaction tx = null;
try {
   tx = session.beginTransaction();
   // do some work
   ...
   tx.commit();
}
catch (Exception e) {
   // Session引发异常则回滚
   if (tx!=null) tx.rollback();
   e.printStackTrace(); 
}finally {
   session.close();
}
```

# 三、MyBatis
> [官方文档](http://www.mybatis.org/mybatis-3/)

**主要的类：**
- SqlSessionFactory
- SqlSession 

**主要的配置文件：**
- mybatis-config.xml：主配置文件
- EntityDao.xml：XML映射文件

## 1. 主配置文件：mybatis-config.xml
配置数据库连接信息和映射文件（包含了 SQL 代码和映射定义信息）
``` xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
  
<configuration>
  <!-- 事务管理和连接池的配置 -->
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="${driver}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
	<!-- mapper 映射器 -->
    <mapper resource="org/xx/config/sqlxml/EntityDao.xml"/>
  </mappers>
</configuration>
```

## 2. XML映射文件：EntityDao.xml
``` xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
<mapper namespace="org.xx.dao.EntityDao">
  <select id="" resultType="">
  </select>
</mapper>
```

## 3. 执行调用
### 1. 构建SqlSessionFactory
``` java
String resource = "org/xx/config/mybatis-config.xml";
InputStream inputStream = Resources.getResourceAsStream(resource);
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
```
> SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由对它进行清除或重建。
> 使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，多次重建 SqlSessionFactory 被视为一种代码“坏味道（bad smell）”。
>  SqlSessionFactory 的最佳作用域是应用作用域。有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。

### 2. 获取SqlSession
``` java
SqlSession session = sqlSessionFactory.openSession();
```
> SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。
> 绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。

### 3. 执行操作（增删改查）
``` java
try {
  UserDao uDao = session.getMapper(UserDao.class);
  List<User> users = uDao.selectAllUsers();
  ... ...
```

### 4. 提交事务
``` java
  session.commit();
```

### 5. 关闭事务
``` java
} finally {
  session.close();
}
```
> 关闭操作是很重要的，应该把关闭操作放到 finally 块中以确保每次都能执行关闭