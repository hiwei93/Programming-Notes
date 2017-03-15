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
**主要的类：**
- SessionFactory 
- Session
- Transaction 
- Query /SQLQuery

**主要的配置文件：**
- hibernate.cfg.xml：主配置文件
- Entity.hbm.xml：映射文件

## 1. 配置数据库连接信息
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
		
		<!-- 配置一对多关联关系 -->
		<!-- many一方的信息 -->
		<set name="" table="">
			<!-- 外键 -->
			<key column="gid"></key>
			<one-to-many class=""/>
		</set>
	</class>
</hibernate-mapping>
```

## 4. 将影射文件配置到主配置文件
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
		sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
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
Session session = factory.openSession();
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

> 完整的例子：
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
   if (tx!=null) tx.rollback();
   e.printStackTrace(); 
}finally {
   session.close();
}
```
> Session引发异常则回滚。