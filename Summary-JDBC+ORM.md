# SSH框架整合
## 一、SSH知识点回顾
1. web层-->Struts2
2. 业务层-->Spring
3. 持久层-->Hibernate

## 二、SSH环境搭建
### 1. 引入相关jar包
1. Struts2框架开发的相应的jar：（基本开发jar包）
- apps-->struts2-blank.war下的jar包；
- 了解的jar包：
- 1. srtuts2-convention-plugin-2.3.15.3.jar：struts2的注解开发jar包（按需）；
- 2. struts2-spring-plugin-2.3.15.3.jar：Struts2用于整合Spring的jar包。

2. Hibernate 框架开发的相应的jar：（Hibernate3）
- hibernate-distribution-3.6.10.Final\hibernate3.jar
- hibernate-distribution-3.6.10.Final\lib\required\*.jar
-  hibernate-distribution-3.6.10.Final\lib\jpa\*.jar
-  slf4j-log4j12-1.7.5.jar：日志记录，slf4j整合log4j的jar包
-  数据库驱动包：mysql-connector-java-5.1.25-bin.jar

3. Spring框架开发的相应的jar：
**基本开发jar包（IOC所需）：**
- spring-beans-3.2.5.RELEASE.jar
- spring-context-3.2.5.RELEASE.jar
- spring-core-3.2.5.RELEASE.jar
- spring-expression-3.2.5.RELEASE.jar
- com.springsource.org.apache.log4j-1.2.15.jar：log4j 日志记录
- com.springsource.org.apache.commons.logging-1.1.1.jar：整合其他日志系统

**AOP所需：**
- spring-aop-3.2.5.RELEASE.jar
- spring-aspects-3.2.5.RELEASE.jar
- com.springsource.org.aopalliance-1.0.0.jar
- com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar

**其他：**
- spring-tx-3.2.5.RELEASE.jar：事务管理
- spring-jdbc-3.2.5.RELEASE.jar
- spring-orm-3.2.5.RELEASE.jar：整合Hibernate
- spring-web-3.2.5.RELEASE.jar：整合web项目
- spring-test-3.2.5.RELEASE.jar：整合jUnit
- com.springsource.com.mchange.v2.c3p0-0.9.1.2.jar：连接池

### 2. 引入相应配置文件
#### 1. Struts2框架的配置文件
web.xml
``` xml
  <!-- Struts2框架的核心过滤器 -->
  <filter>
    <filter-name>struts2</filter-name>
    <filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>struts2</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```
struts.xml

#### 2. Hibernate框架的配置文件
- hibernate.cfg.xml（该项目中可以省略）
- 数据库表映射文件

#### 3. Spring配置文件
web.xml
``` xml
  <!-- Spring 框架的核心监听器 -->
  <listener>
  	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  
  <context-param>
  	<param-name>contextConfigLocation</param-name>
  	<!-- 指向编译路径下的src，即.class文件目录的src下 -->
  	<param-value>classpath:applicationContext.xml</param-value>
  </context-param>
```
applicationContext.xml
spring全部约束：
``` xml
<?xml version="1.0" encoding="utf-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:aop="http://www.springframework.org/schema/aop"
        xmlns:tx="http://www.springframework.org/schema/tx"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd
        http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx.xsd">
</beans>
```

#### 4. 其他配置文件
log4j.properties

### 3. 创建包结构
com.ssh
- action
- service
- dao
- entity

## 三、Struts2整合Spring
### 1. 创建页面
addProduct.jsp
``` xml
<%@ taglib uri="/struts-tags" prefix="s" %>
<h1>保存商品的页面</h1>
	<s:form action="product_save" method="post" namespace="/" theme="simple">
		<table border="1" width="400">
			<tr>
				<td>商品名称</td>
				<td><s:textfield name="pname"></s:textfield></td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td><s:textfield name="price"></s:textfield></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="添加"/></td>
			</tr>
		</table>
	</s:form>
```

### 2. 编写Action、Service和DAO
#### 1. Service层注入DAO层
productService.java
``` java
public class ProductService {	
	// 业务层注入DAO类
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
}
```
> 提供set方法即可注入；

#### 2. Action层注入Service层
productAction.java
``` java
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	// 模型驱动使用的类
	private Product product = new Product();
	@Override
	public Product getModel() {
		return product;
	}
	
	// Struts2和Spring整合过程中按名称自动注入的业务层类
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}
```
> 模型驱动必须要实例化，即必须new！

### 3. 配置Action、Service和DAO
Service和DAO交给Spring管理：applicationContext.xml
``` xml
	<!-- 配置业务层的类 -->
	<bean id="productService" class="com.wei.service.ProductService">
		<property name="productDao" ref="productDao"></property>
	</bean>
	
	<!-- 配置DAO的类 -->
	<bean id="productDao" class="com.wei.dao.ProductDao">
	</bean>
```

Struts2和Spring整合的两种方式：
1. Action的类有Struts2自身来创建：struts.xml
``` xml
<struts>
    <package name="ssh" namespace="/" extends="struts-default">
		<action name="product_*" class="com.ssh.action.ProductAction" method="{1}">
			
		</action>
    </package>
</struts>
```
> 此处action的class属性填写类的全路径

2. Action的类交给Spring来创建
applicationContext.xml
``` xml
	<!-- 配置Action的类 -->
	<bean id="productAction" class="com.wei.action.ProductAction" scope="prototype">
		<!-- 手动注入Service -->
		<property name="productService" ref="productService"></property>
	</bean>
```

struts.xml
``` xml
<struts>
    <package name="ssh" namespace="/" extends="struts-default">
		<action name="product_*" class="productAction" method="{1}">
			
		</action>
    </package>
</struts>
```
> 1. 此处action的class属性填写类在applicationContext.xml配置的id名；
> 2. Spring创建action是单例的，要配置属性scope="prototype"。

## 四、Spring整合Hibernate
### 1. 创建数据库

### 2. 创建表与实体的映射文件
在hibernate-mapping-3.0.dtd中获取 Hibernate约束
Product.hbm.xml
``` xml
<hibernate-mapping>
	<class name="com.wei.domain.Product" table="product">
		<id name="pid" column="pid">
			<generator class="native"></generator>
		</id>
		
		<property name="pname" column="pname" length="20"></property>
		<property name="price" column="price"></property>
	</class>
</hibernate-mapping>
```

### 3. Spring整合Hibernate
#### 1. 配置数据库连接参数
jdbc.properties
```
jdbc.driverClass=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/ssh
jdbc.username=root
jdbc.password=root
```

#### 2. 配置连接池：applicationContext.xml
``` xml
	<!-- 引入外部属性文件 -->
	<context:property-placeholder location="classpath:jdbc.properties"/>
	
	<!-- 配置c3p0连接池 -->
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="driverClass" value="${jdbc.driverClass}"></property>
		<property name="jdbcUrl" value="${jdbc.url}"></property>
		<property name="user" value="${jdbc.username}"></property>
		<property name="password" value="${jdbc.password}"></property>
	</bean>
```

#### 3. 配置Hibernate相关属性：applicationContext.xml
``` xml
<!-- 配置Hibernate相关属性 -->
	<bean id="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
		<!-- 注入连接池 -->
		<property name="dataSource" ref="dataSource"></property>
		<!-- 配置Hibernate属性 -->
		<property name="hibernateProperties">
			<props>
				<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
				<prop key="hibernate.show_sql">true</prop>
				<prop key="hibernate.format_sql">true</prop>
				<prop key="hibernate.hbm2ddl.auto">update</prop>
			</props>
		</property>
		<!-- 加载Hibernate中的映射文件 -->
		<property name="mappingResources">
			<list>
				<value>com/wei/domain/Product.hbm.xml</value>
			</list>
		</property>
	</bean>
```

#### 4. 编写DAO
使用Hibernate模板
注入sessionFactory：applicationContext.xml
``` xml
	<!-- 配置DAO的类 -->
	<bean id="productDao" class="com.wei.dao.ProductDao">
		<property name="sessionFactory" ref="sessionFactory"></property>
	</bean>
```
ProductDao.java
``` java
public class ProductDao extends HibernateDaoSupport{
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}
}
```
> DAO层需要继承HibernateDaoSupport类；

> 注：Hibernate4之后的版本将不再支持HibernateTemplate，则Dao类需要如下：
``` xml
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Collection loadProductsByCategory(String category) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("from test.Product product where product.category=?")
                .setParameter(0, category)
                .list();
    }
}
```

#### 5. 事务管理
1. 配置事务管理器：applicationContext.xml
``` xml
	<!-- 配置事务管理器 -->
	<bean id="transactionManager" class="org.springframework.orm.hibernate3.HibernateTransactionManager">
		<property name="sessionFactory" ref="sessionFactory"></property>
	</bean>
```
2. 开启注解事务：applicationContext.xml
```  xml
	<!-- 开启注解事务 -->
	<tx:annotation-driven transaction-manager="transactionManager"/>
```
3. 在事务管理的类（即业务层）上添加注解@Transactional
ProductService.java
``` java
@Transactional
public class ProductService {
}
```