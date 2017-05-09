# MyBatis 动态SQL
> [官方文档](http://www.mybatis.org/mybatis-3/zh/dynamic-sql.html)

- if
- choose (when, otherwise)
- trim (where, set)
- foreach

## 1. if
如果满足test条件则执行
``` xml
<select id="findActiveBlogWithTitleLike"
     resultType="Blog">
  SELECT * FROM BLOG 
  WHERE state = ‘ACTIVE’ 
  <if test="title != null">
    AND title like #{title}
  </if>
</select>
```

## 2. choose, when, otherwise
根据test条件去选择执行，可以与Java中的switch语句对比理解。
``` xml
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG WHERE state = ‘ACTIVE’
  <choose>
    <when test="title != null">
      AND title like #{title}
    </when>
    <when test="author != null and author.name != null">
      AND author_name like #{author.name}
    </when>
    <otherwise>
      AND featured = 1
    </otherwise>
  </choose>
</select>
```

## 3. trim, where, set
### 1. where
- where元素可以替代SQL中的where；
- where元素可以自动判断是否去除内容首部的`OR`或`AND`；
- 与if元素结合使用

``` xml
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG 
  <where> 
    <if test="state != null">
         state = #{state}
    </if> 
    <if test="title != null">
        AND title like #{title}
    </if>
    <if test="author != null and author.name != null">
        AND author_name like #{author.name}
    </if>
  </where>
</select>
```

### 2. set
- set元素可以替代SQL中的set，常用于update操作；
- set元素可以自动判断是否舍去`,`；

``` xml
<update id="updateAuthorIfNecessary">
  update Author
    <set>
      <if test="username != null">username=#{username},</if>
      <if test="password != null">password=#{password},</if>
      <if test="email != null">email=#{email},</if>
      <if test="bio != null">bio=#{bio}</if>
    </set>
  where id=#{id}
</update>
```

### 3. trim
trim元素可以替换where元素和set元素

1. trim替换where
``` xml
<trim prefix="WHERE" prefixOverrides="AND |OR ">
  ... 
</trim> 
```
prefixOverrides：会根据条件忽略分割文本序列的`AND`或者`OR`前缀。

2. trim替换set
``` xml
<trim prefix="SET" suffixOverrides=",">
  ...
</trim>
```
suffixOverrides：会根据条件忽略分割文本序列的`，`后缀。


## 4. foreach
集合遍历
``` xml
<select id="selectPostIn" resultType="domain.blog.Post">
  SELECT *
  FROM POST P
  WHERE ID in
  <foreach item="item" index="index" collection="list"
      open="(" separator="," close=")">
        #{item}
  </foreach>
</select>
```
当使用可迭代对象或者数组时，index是当前迭代的次数，item的值是本次迭代获取的元素。
当使用Map时，index是键，item是值。

## 5. bind
bind 元素可以从 OGNL 表达式中创建一个变量并将其绑定到上下文
``` xml
<select id="selectBlogsLike" resultType="Blog">
  <bind name="pattern" value="'%' + _parameter.getTitle() + '%'" />
  SELECT * FROM BLOG
  WHERE title LIKE #{pattern}
</select>
```