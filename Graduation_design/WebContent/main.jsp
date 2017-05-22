<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.ysu.entity.User" %>
<%@page import="com.ysu.entity.Admin" %>
<%@page import="com.ysu.entity.Classification" %>
<%@page import="com.ysu.entity.Book" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="css/main.css" type="text/css"></link>
<script src="css/jquery-1.9.0.min.js"></script>
<script type="text/javascript">

function openLoginPage(){
	var k = window.showModalDialog('userlogin.jsp','dialogWidthwidth=200,dialogWidthheight=200');
	if (k == 1) {
		window.location.href = "<%=request.getContextPath()%>/index";
	}
}

function openAdminLoginPage () {
	var k = window.showModalDialog('adminlogin.jsp','dialogWidthwidth=200,dialogWidthheight=200');
	if (k == 1) {
		window.location.href = "<%=request.getContextPath()%>/index";
	}
}

function searchBook(name) {
 	$("#search").val(name);
 	$("#searchForm").submit();
	
}
</script>
<%
	if (session.getAttribute("loginUser") == null) {
		
	}
%>

  <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>
</head>
<body>
<div class="a1">
  <div class="a2"><img src="img/main.c.png" width="125" height="128"></img></div>
  <div class="a3">
    <p><font  color="#FFFFFF" size="+3"> &nbsp; 燕山大学里仁学院图书馆</font></p>
    <p><font color="#FFFFFF" size="+1">Benevolence in yanshan university college library</font></p>
  </div>
  
  <div class="a4" id="login">
  <font color="#FFFFFF">
	<%
	if (session.getAttribute("loginUser") != null) {
	%>
	<font color="ffffff">欢迎&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/user?type=showUser" target="_blank"><%=((User)session.getAttribute("loginUser")).getUser_name() %></a></font>
	<%
	} else if (session.getAttribute("adminUser") != null) {
	%>
	<font color="ffffff">欢迎管理员&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/manager.jsp"><%=((Admin)session.getAttribute("adminUser")).getAdmin_name() %></a></font>
	<%	
	} else {
	%>
	 <h3> <a href="javascript:void(0)" onclick="openLoginPage()"><font color="ffffff">登录</font></a>&nbsp;&nbsp;
 	<a href="javascript:void(0)" onclick="openAdminLoginPage()"><font color="ffffff">管理员登录</font></a></h3>
	<%
	}
	%>

  </font></div>
</div>


<div class="a5"><img src="img/a.gif" width="935" height="282"></img></div>

<div class="a6">
<form action="<%=request.getContextPath()%>/book?type=search" method="post" id="searchForm">
  <h1>
    <input name="search" id="search" type="text"  style="width:300px; height:30px;"/>
    &nbsp;
  <input type="submit" name="Submit" value="查找"  style="width:60px; height:30px; color:#FFFFFF; background-color:#FF0000;"/>
  </h1>
  </form>
</div>

<div class="a8" align="center"><font color="#0033CC" size="+3">新书推荐</font><table width="310" border="0">
  <%
  	List<Book> hotBookList = (List<Book>)request.getAttribute("newBook");
  	if (hotBookList != null) {
  		for (Book book:hotBookList) {
  %>
  <tr>
    <td width="104"><a href="<%=request.getContextPath()%>/book?bookId=<%=book.getBook_id() %>&type=getSingleBook"><img src="img/<%=book.getCoverPicture() %>"width="82" height="95"></img></a>&nbsp;</td>
    <td width="190">&nbsp;<a href="<%=request.getContextPath()%>/book?bookId=<%=book.getBook_id() %>&type=getSingleBook"><%=book.getBook_name() %></a></td>
  </tr>
  <%
  		}
  	}
  %>
</table>


</div>
<div class="a9" align="center"><font color="#0033CC" size="+3">热门借阅</font>
  <table width="308" height="315" border="0">
  <%
  	List<Book> bookList = (List<Book>)request.getAttribute("hotBook");
  	if (bookList != null) {
  		for (Book book:bookList) {
  %>
  <tr>
    <td width="298">&nbsp;<img src="img/main.e.png"width="21" height="17"></img>&nbsp;
    <a href="<%=request.getContextPath()%>/book?bookId=<%=book.getBook_id() %>&type=getSingleBook"><%=book.getBook_name() %></a>
    </td>
    
  </tr>
  <%
  		}
  	}
  %>
</table></div>

<div class="a10" align="center" ><font color="#0033CC" size="+3">热门分类</font>
  <table width="309" height="316" border="0">
  <%
  	List<Classification> classificationList = (List<Classification>)request.getAttribute("hotClassification");
  	if (classificationList != null) {
		for (Classification classification:classificationList) {
  %>
  <tr>
    <td width="299">&nbsp;<img src="img/main.e.png"width="21" height="17"><font size="+1">&nbsp;<a href="javascript:void(0)" onclick="return searchBook('<%=classification.getClassificationName()%>')"><%=classification.getClassificationName()%></a></font></td>
  </tr>
  <%
		}
	}
  %>
</table></div>
<div class="a50"><a href=""><font color="#999999">文学类</font></a>&nbsp;&nbsp;<a href=""><font color="#999999">专业类</font></a></div>
<div class="a11" align="center"><br>
<font size="4">燕山大学里仁学院</font><br><br>
<font size="5">版权所有©翻版必究</font></div>

  </body>
</html>
