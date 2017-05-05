# MyBatis配置文件
> [官方文档](http://www.mybatis.org/mybatis-3/zh/configuration.html)

[TOC]

XML文档约束
``` xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
```

## 1. 根节点 `<configuration></configuration>`

## 2. properties 属性
### 1. 引入外部配置文件
``` xml
<properties resource="org/xx/config/config.properties">
  <property name="username" value=""/>
  <property name="password" value=""/>
</properties>
```
> 参数可以通过`<property>`子标签传递

### 2. 通过Java文件配置属性
属性也可以被传递到 SqlSessionFactoryBuilder.build()方法中：
``` java
SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, props);

// ... or ...

SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, environment, props);
```

### 3. MyBatis属性配置加载的顺序
1. 在 properties 元素体内指定的属性首先被读取；
2. 然后根据 properties 元素中的 resource 属性读取类路径下属性文件或根据 url 属性指定的路径读取属性文件，并覆盖已读取的同名属性；
3. 最后读取作为方法参数传递的属性，并覆盖已读取的同名属性。

### 4. 为占位符指定一个默认值
MyBatis 3.4.2之后的版本支持
1. 开启该功能
``` xml
<properties resource="org/xx/config/config.properties">
  <!-- ... -->
  <property name="org.apache.ibatis.parsing.PropertyParser.enable-default-value" value="true"/> <!-- Enable this feature -->
  <property name="org.apache.ibatis.parsing.PropertyParser.default-value-separator" value="?:"/> <!-- Change default value of separator -->
</properties>
```
> 默认的分隔符是`:`，可以对分隔符进行更改，如上例中就改为了`?:`。

2. 设置默认值

``` xml
<dataSource type="POOLED">
  <!-- ... -->
  <property name="username" value="${username:ut_user}"/> <!-- If 'username' property not present, username become 'ut_user' -->
</dataSource>
```

## 3. settings 设置
这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为
常用的几个setting：
- useColumnLabel：使用列标签代替列名。不同的驱动在这方面会有不同的表现， 具体可参考相关驱动文档或通过测试这两种不同的模式来观察所用驱动的结果，默认为true。
- useGeneratedKeys：允许 JDBC 支持自动生成主键，需要驱动兼容，默认为false。
- mapUnderscoreToCamelCase：是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN 到经典 Java 属性名 aColumn 的类似映射，默认为false。

``` xml
<settings>
   <setting name="" value="" />
   ... ...
</setting>
```

## 4. typeAliases 类型别名
用来减少类完全限定名的冗余
### 1. 设置类型别名
``` xml
<typeAliases>
  <typeAlias alias="User" type="org.entity.User"/>
</typeAliases>
```
配置后`User`可以用在`org.entity.User`使用的地方

### 2. 指定一个包名
MyBatis 会在包名下面搜索需要的 Java Bean
``` xml
<typeAliases>
  <package name="org.entity"/>
</typeAliases
```

## 5. typeHandlers 类型处理器
可以重写类型处理器或创建你自己的类型处理器来处理不支持的或非标准的类型。
1. 实现 org.apache.ibatis.type.TypeHandler 接口， 或继承一个很便利的类 org.apache.ibatis.type.BaseTypeHandler。
2. 选择性地将它映射到一个 JDBC 类型。
> 具体操作请看[MyBatis官方文档](http://www.mybatis.org/mybatis-3/zh/configuration.html)

## 6. objectFactory 对象工厂

## 7. plugins 插件

## 8. environments 环境
``` xml
<environments default="development">
  <environment id="development">
    <transactionManager type="JDBC">
      <property name="..." value="..."/>
    </transactionManager>
    <dataSource type="POOLED">
      <property name="driver" value="${driver}"/>
      <property name="url" value="${url}"/>
      <property name="username" value="${username}"/>
      <property name="password" value="${password}"/>
    </dataSource>
  </environment>
</environments>
```
1. 可以有多个`<environment id="">`，id的值为环境名称，如development（开发环境）、production（生产环境）；
2. 创建SqlSessionFactory可以指定使用哪种环境下的配置，如不设置则为默认环境
``` java
SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, environment);
SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, environment,properties);
```

### 1. transactionManager：事务管理器
在 MyBatis 中有两种类型的事务管理器（也就是 type=”[JDBC|MANAGED]”）：
- JDBC ：这个配置就是直接使用了 JDBC 的提交和回滚设置，它依赖于从数据源得到的连接来管理事务作用域。
- MANAGED：这个配置几乎没做什么。它从来不提交或回滚一个连接，而是让容器来管理事务的整个生命周期（比如 JEE 应用服务器的上下文）。 默认情况下它会关闭连接，然而一些容器并不希望这样，因此需要将 closeConnection 属性设置为 false 来阻止它默认的关闭行为。

> 使用 Spring + MyBatis，则没有必要配置事务管理器， 因为 Spring 模块会使用自带的管理器来覆盖前面的配置。

### 2. dataSource：数据源
有三种内建的数据源类型（也就是 type=”[UNPOOLED|POOLED|JNDI]”）：
1. UNPOOLED：这个数据源的实现只是每次被请求时打开和关闭连接。虽然一点慢，它对在及时可用连接方面没有性能要求的简单应用程序是一个很好的选择。 不同的数据库在这方面表现也是不一样的，所以对某些数据库来说使用连接池并不重要，这个配置也是理想的。UNPOOLED 类型的数据源仅仅需要配置以下 5 种属性：
- driver：这是 JDBC 驱动的 Java 类的完全限定名（并不是JDBC驱动中可能包含的数据源类）。
- url：这是数据库的 JDBC URL 地址。
- username：登录数据库的用户名。
- password：登录数据库的密码。
- defaultTransactionIsolationLevel：默认的连接事务隔离级别。

> 作为可选项，你也可以传递属性给数据库驱动。要这样做，属性的前缀为“driver.”，例如：driver.encoding=UTF8
> 这将通过DriverManager.getConnection(url,driverProperties)方法传递值为 UTF8 的 encoding 属性给数据库驱动。

2. POOLED– 这种数据源的实现利用“池”的概念将 JDBC 连接对象组织起来，避免了创建新的连接实例时所必需的初始化和认证时间。 这是一种使得并发 Web 应用快速响应请求的流行处理方式。
除了上述提到 UNPOOLED 下的属性外，会有更多属性用来配置 POOLED 的数据源。
> 具体操作请看[MyBatis官方文档](http://www.mybatis.org/mybatis-3/zh/configuration.html)

3. JNDI– 这个数据源的实现是为了能在如 EJB 或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个 JNDI 上下文的引用。

## 9. databaseIdProvider 数据库厂商标识
MyBatis 可以根据不同的数据库厂商执行不同的语句，这种多厂商的支持是基于映射语句中的 databaseId 属性。
``` xml
<databaseIdProvider type="DB_VENDOR">
  <property name="SQL Server" value="sqlserver"/>
  <property name="DB2" value="db2"/>        
  <property name="Oracle" value="oracle" />
</databaseIdProvider>
```

## 10. mappers 映射器
映射器
配置映射文件路径
``` xml
<mappers>
  <!-- Using classpath relative resources -->
  <mapper resource="org/xx/config/mappers/UserDao.xml"/>

  <!-- Using url fully qualified paths -->
  <mapper url="file:org/xx/config/mappers/AuthorMapper.xml"/>

  <!-- Register all interfaces in a package as mappers -->
  <package name="org/xx/config/mappers"/>
</mappers>
```