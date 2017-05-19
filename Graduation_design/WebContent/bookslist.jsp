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

  <link rel="stylesheet" href="css/bookslist.css" type="text/css"></link></head>
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

<div class="a70"></div>

<div class="a71" ><font size="+3" >图书列表:</font></div>

<div class="a72"><table width="999" height="256" border="1">
  <tr>
    <td width="200" height="62" align="center"><font size="+1" >图书封面</font></td>
    <td width="200" align="center"><font size="+1" >图书名</font></td>
    <td width="200" align="center"><font size="+1" >作者</font></td>
    <td width="200" align="center"><font size="+1" >分类</font></td>
    <td width="200" align="center"><font size="+1" >详情</font></td>
  </tr>
  <tr>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
    <td align="center"><a href=""><font size="+1" >详细信息</font></a></td>
  </tr>
</table>
</div>
</body>
</html>
