<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'personal.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  <link rel="stylesheet" href="css/booksdetails.css" type="text/css"></link></head>
   <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>

<body>
<div class="a17">
  <div class="a18"><img src="img/main.c.png" width="129" height="112" /></div>
  
  <div class="a19">
    <p><font size="+3" color="#000066"> &nbsp;燕山大学里仁学院图书馆</font></p>
    <p><font size="+1" color="#000066">Benevolence in yanshan university college library</font></p>
  </div>
</div>
<div class="a20"><font size="+1"><a href="">首页</a>&nbsp;&nbsp;&nbsp;<a href="">返回</a></font> </div>


<div class="a75"></div>

<div class="a76">(图书封面位置)</div>

<div class="a77"><table width="465" height="304" border="1">
  <tr>
    <td>&nbsp;<font size="+3">(图书名位置)</font></td>
  </tr>
  <tr>
    <td>&nbsp;<font size="+1">作者：(作者位置)</font></td>
  </tr>
  <tr>
    <td>&nbsp;<font size="+1">分类：（图书分类位置）</font></td>
  </tr>
  <tr>
    <td>&nbsp;<font size="+1">图书ID：（图书号位置）</font></td>
  </tr>
  <tr>
    <td>&nbsp;<font size="+1">位置：（图书摆放位置）</font></td>
  </tr>
  <tr>
    <td>&nbsp;<font size="+1">剩余数量：（剩余数量位置）</font></td>
  </tr>
  <tr>
    <td>&nbsp;<a href=""><font size="+2">借阅</font></a></td>
  </tr>
</table>
</div>
</body>
</html>
