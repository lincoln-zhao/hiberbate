<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.ysu.entity.Book" %>
<%@page import="com.ysu.entity.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manager2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="css/manager2.css" type="text/css"></link>
   <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>
  <script type="text/javascript">
	<%if (session.getAttribute("adminUser") == null){ %>
	alert("管理员未登录！");
	window.location.href = "<%=request.getContextPath()%>/main.jsp";
	<%}
	
	if (request.getAttribute("returnBookResult") != null) {
	%>
	alert('<%=request.getAttribute("returnBookResult") %>');
	<%
	}
	%>
  </script>
  </head>
  <body>
<div class="a33"><img src="img/manager.b.jpg" width="1326" height="645" /></div>

<div class="a34" style="border-color:#000033; border-right-style:solid;border-width:medium;"></div>

<div class="a37"><table width="310" height="299" border="0">
  <tr>
    <td width="44" height="65"><img src="img/manager.a.png" width="32" height="29" /></td>
    <td width="250"><a href="<%=request.getContextPath()%>/user?type=allUser"><font size="+2">用户管理</font></a></td>
  </tr>
  <tr>
    <td width="44" height="65"><img src="img/manager.a.png" width="32" height="29" /></td>
    <td><a href="<%=request.getContextPath()%>/book?type=allBorrowBook"><font size="+2">借还书管理</font></a></td>
  </tr>
  <tr>
    <td width="44" height="65"><img src="img/manager.a.png" width="32" height="29" /></td>
    <td><a href="<%=request.getContextPath()%>/book?type=allBooks"><font size="+2">图书管理</font></a></td>
  </tr>
  <tr>
    <td width="44" height="65"><img src="img/manager.a.png" width="32" height="29" /></td>
	<td><a href="<%=request.getContextPath()%>/book?type=getAllClassification"><font size="+2">分类管理</font></a></td>
</table>
</div>

<div class="a35"><font size="+1"><font size="+1">管理员：<%if (session.getAttribute("adminUser") != null){ %><%=((Admin)session.getAttribute("adminUser")).getAdmin_name() %><%} %></font>&nbsp;&nbsp;&nbsp;<a href="index">首页</a>&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/user?type=logout">登出</a></font><br/></div>

<div class="a36"><font color="#623131" size="+2">借还书管理</font></div>

<div class="a38" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a39" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a40" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a41" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a43">
<table width="881" height="127" border="1" cellpadding="0" cellspacing="0" bordercolor="#9E9E9E">
  <tr>
    <td width="110"><font size="+1">&nbsp;&nbsp;&nbsp;用户名</font></td>
	<td width="110"><font size="+1">&nbsp;&nbsp;借书书号</font></td>
	<td width="110"><font size="+1">&nbsp;&nbsp;借书书名</font></td>
	<td width="110"><font size="+1">&nbsp;&nbsp;图书作者</font></td>
    <td width="110"><font size="+1">&nbsp;&nbsp;借书时间</font></td>
    <td width="110"><font size="+1">&nbsp;&nbsp;还书时间</font></td>
    <td width="110"><font size="+1">&nbsp;&nbsp;借书状态</font></td>
    <td width="110"><font size="+1">&nbsp;&nbsp;操作</font></td>
  </tr>
  <%List<Book> bookList = (List<Book>)request.getAttribute("bookList");
  	if (bookList!= null) {
  		for (Book book:bookList) {
  %>
  <tr>
    <td><%=book.getUser_name() %></td>
    <td><%=book.getBook_id() %></td>
    <td><%=book.getBook_name() %></td>
	<td><%=book.getAuthor() %></td>
    <td><%=book.getStart_date() %></td>
    <td><%=book.getEnd_date() %></td>
    <%if (book.getEnd_date().before(new Date())) {  %>
    <td>超期</td>
    <%} else { %>
    <td>&nbsp;</td>
    <%} %>
    <td><a href="<%=request.getContextPath()%>/book?type=returnBook&bookId=<%=book.getBook_id() %>&userId=<%=book.getUser_id() %> ">归还</a></td>
  </tr>
  <%
  		}
  	}
  %>
</table>

</div>
  </body>
</html>
