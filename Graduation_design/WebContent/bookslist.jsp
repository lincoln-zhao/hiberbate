<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.ysu.entity.Book" %>
<%@page import="com.ysu.entity.User" %>
<%@page import="com.ysu.entity.Admin" %>
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
  <div class="a4" id="login" style="height: 41px;left: 1114px;position: absolute;top: 3px;width: 239px;">
  <font color="#000000">
	<%
	if (session.getAttribute("loginUser") != null) {
	%>
	<font color="#000000">欢迎&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/user?type=showUser" target="_blank"><%=((User)session.getAttribute("loginUser")).getUser_name() %></a></font>
	<%
	} else if (session.getAttribute("adminUser") != null) {
	%>
	<font color="#000000">欢迎管理员&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/manager.jsp"><%=((Admin)session.getAttribute("adminUser")).getAdmin_name() %></a></font>
	<%	
	} else {
	%>
	 <h3> <a href="javascript:void(0)" onclick="openLoginPage()"><font color="#000000">登录</font></a>&nbsp;&nbsp;
 	<a href="javascript:void(0)" onclick="openAdminLoginPage()"><font color="#000000">管理员登录</font></a></h3>
	<%
	}
	%>

  </font></div>
</div>
 

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
  <%
  List<Book> bookList = (List<Book>) request.getAttribute("bookList");
  if (bookList != null && bookList.size() > 0) {
	  for (Book book:bookList) {
  %>
  <tr>
    <td align="center"><img src="img/<%=book.getCoverPicture()%>" width="129" height="112" /></td>
    <td align="center"><%=book.getBook_name() %></td>
    <td align="center"><%=book.getAuthor() %></td>
    <td align="center"><%=book.getClassification() %></td>
    <td align="center"><a href="<%=request.getContextPath()%>/book?bookId=<%=book.getBook_id() %>&type=getSingleBook"><font size="+1" >详细信息</font></a></td>
  </tr>
  <%
	  }
  } else {
  %>
  搜索结果为空。
  <%} %>
</table>
</div>
</body>
</html>
