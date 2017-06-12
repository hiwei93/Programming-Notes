<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <h2>输入 n 个数字，以逗号，分开</h2>
    <form action="servlet/SortServlet" method="POST">
    	<input type="text" name="array">
    	<input type="radio" name="sortType" value="1" checked="checked">升序
    	<input type="radio" name="sortType" value="0">降序<br>
    	<input type="submit" value="提交">&nbsp;&nbsp;&nbsp;
    	<input type="reset" value="重置">
    </form>
  </body>
</html>
