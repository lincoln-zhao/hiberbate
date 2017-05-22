<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.ysu.entity.User" %>
<%@page import="com.ysu.entity.Admin" %>
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

  <link rel="stylesheet" href="css/booksdetails.css" type="text/css"></link>
   <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>
  <%Book book = (Book)request.getAttribute("book"); %>
  <script src="css/jquery-1.9.0.min.js"></script>
  <script type="text/javascript">
  	<% if (book == null){ %>
  		alert("图书为未找到，请与管理员联系！");
  		window.location.href = "<%=request.getContextPath()%>/index";
  	<% } %>
  	
  	function borrowBook (bookId) {
  		<%User user = (User)session.getAttribute("loginUser"); %>
  		<%
  		if (user == null) {
  		%>
  		alert("用户未登录！");
  		window.location.href = "<%=request.getContextPath()%>/index";
  		return false;
  		<%
  		}
  		%>
  		$.ajax({
			type:'post',
			data:{userId:<%=user == null?"''":user.getUser_id() %>,bookId:bookId,type:"borrowBook"},
			url:"<%=request.getContextPath()%>/book",
			success:function(data){
				if (data == 'success') {
					alert("借书成功！");
					window.location.href = "<%=request.getContextPath()%>/user?type=showUser"
				} else {
					alert(data);
				}
			},
			error:function () {
				alert("系统错误，请联系管理员。");
			}
		});
  		
  	}
  	
  	function openLoginPage(){
  		var k = window.showModalDialog('userlogin.jsp','dialogWidthwidth=200,dialogWidthheight=200');
  		if (k == 1) {
  			window.location.reload();
  		}
  	}

  	function openAdminLoginPage () {
  		var k = window.showModalDialog('adminlogin.jsp','dialogWidthwidth=200,dialogWidthheight=200');
  		if (k == 1) {
  			window.location.reload();
  		}
  	}
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


<div class="a75"></div>
<% if (book != null){ %>
<div class="a76"><img src="img/<%=book.getCoverPicture() %>" width="350" height="400" /></div>

<div class="a77"><table width="465" height="304" border="1">

  <tr>
    <td>&nbsp;<font size="+3"><%=book.getBook_name() %></font></td>
  </tr>
  <tr>
    <td>&nbsp;<font size="+1">作者：<%=book.getAuthor() %></font></td>
  </tr>
  <tr>
    <td>&nbsp;<font size="+1">分类：<%=book.getClassification() %></font></td>
  </tr>
  <tr>
    <td>&nbsp;<font size="+1">图书ID：<%=book.getBook_id() %></font></td>
  </tr>
  <tr>
    <td>&nbsp;<font size="+1">位置：<%=book.getPosition() %></font></td>
  </tr>
  <tr>
    <td>&nbsp;<font size="+1">上架时间：<%=book.getAdd_date() %></font></td>
  </tr>
  <tr>
    <td>&nbsp;<a href="javascript:void(0)" onclick="borrowBook('<%=book.getBook_id() %>')"><font size="+2">借阅</font></a></td>
  </tr>

</table>
</div>
  <%} %>
</body>
</html>
