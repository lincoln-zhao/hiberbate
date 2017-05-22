<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.ysu.entity.User" %>
<%@page import="com.ysu.entity.Book" %>
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

  <link rel="stylesheet" href="css/personal.css" type="text/css"></link>
   <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>
  <%User user = (User)session.getAttribute("loginUser"); %>
  <script src="css/jquery-1.9.0.min.js"></script>
  <script type="text/javascript">
  	$(function () {
  		<%
  		if (user == null) {
  		%>
  		alert("用户未登录！");
  		window.location.href = "<%=request.getContextPath()%>/index";
  		<%
  		}
  		
  		if (request.getAttribute("returnBookResult") != null) {
  		%>
  		alert('<%=request.getAttribute("returnBookResult") %>');
  		<%
  		}
  		%>
  	})
  </script>
</head>
  <body>
<div class="a17">
  <div class="a18"><img src="img/main.c.png" width="129" height="112" /></div>
  
  <div class="a19">
    <p><font size="+3" color="#000066"> &nbsp;燕山大学里仁学院图书馆</font></p>
    <p><font size="+1" color="#000066">Benevolence in yanshan university college library</font></p>
  </div>
</div>
<div class="a20"><font size="+1"><a href="index">首页</a>&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/user?type=logout">登出</a></font> </div>

<div class="a27" style="border-color:#000033; border-right-style:solid;border-left-style:solid; border-width:medium;">
  <p><font size="+3"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 个人信息</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="update.jsp"><font size="+1">修改个人信息</font></a></p>
</div>

<div class="a28"></div>

<div class="a29" style="border-color:#000033; border-right-style:solid;border-top-style:solid;border-left-style:solid; border-width:medium;">

</div>

<div class="a32"><table width="361" height="294" border="1">
<%if (user != null){ %>
  <tr>
    <td width="180px">用户名：</td>
    <td><%=user.getUser_name() %></td>
  </tr>
  <tr>
    <td>性别：</td>
    <td><%=user.getSex() %></td>
  </tr>
  <tr>
    <td>联系电话：</td>
    <td><%=user.getPhone() %></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <%} %>
</table>
</div>
<div class="a30" align="center"><font size="+3">当前借书</font></div>
<div class="a31">
  <table width="855" height="159" border="1" align="center">
  <tr>
    <td width="163"><font size="+3"> &nbsp;图书编号</font></td>
    <td width="163"><font size="+3">&nbsp;&nbsp;图书名</font></td>
    <td width="163"><font size="+3">&nbsp;借书时间</font></td>
    <td width="166"><font size="+3">&nbsp;最晚还书时间</font></td>
    <td width="166"><font size="+3">&nbsp;操作</font></td>
  </tr>

  <%
  List<Book> nowBookList = (List<Book>)request.getAttribute("nowBookList");
  	if (nowBookList != null) {
  		for (Book book:nowBookList) {
  %>
  <tr>
    <td><%=book.getBook_id() %></td>
    <td><%=book.getBook_name() %></td>
	<td><%=book.getStart_date() %></td>
	<td><%=book.getEnd_date() %></td>
	<%
		if (book.getEnd_date().after(new Date())) {
	%>
	<td><a href="<%=request.getContextPath()%>/book?type=returnBook&bookId=<%=book.getBook_id() %>">归还</a></td>
	<%
		} else {
	%>
	<td><a href="javascript:alert('图书超期，请与管理员联系。')">归还</a></td>
	<%
		}
	%>
  </tr>
  <%
  		}
  	} else {
  %>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  <%
  	}
  %>

</table>
</div>
<div class="a30" align="center" style="top:420px;"><font size="+3">借书历史</font></div>
<div class="a31" style="top:480px;">
  <table width="855" height="159" border="1" align="center">
  <tr>
    <td width="163"><font size="+3"> &nbsp;图书编号</font></td>
    <td width="163"><font size="+3">&nbsp;&nbsp;图书名</font></td>
    <td width="163"><font size="+3">&nbsp;借书时间</font></td>
    <td width="166"><font size="+3">&nbsp;还书时间</font></td>
  </tr>
  <%
  	List<Book> bookList = (List<Book>)request.getAttribute("bookList");
  	if (bookList != null) {
  		for (Book book:bookList) {
  %>
  			<tr>
			    <td><%=book.getBook_id() %></td>
			    <td><%=book.getBook_name() %></td>
			    <td><%=book.getStart_date() %></td>
			    <td><%=book.getEnd_date() %></td>
			  </tr>
  <%
  		}
  	}
  %>
</table>
</div>
  </body>
</html>
