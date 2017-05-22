<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    
    <title>My JSP 'manager1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  <link rel="stylesheet" href="css/manager1.css" type="text/css"></link>
  
  <script src="css/jquery-1.9.0.min.js"></script>
  <script type="text/javascript">
  	<%if (session.getAttribute("adminUser") == null){ %>
	alert("管理员未登录！");
	window.location.href = "<%=request.getContextPath()%>/main.jsp";
	<%}%>
  	function delUser (userId) {
		$.ajax({
			type:'post',
			data:{userId:userId,type:"delUser"},
			url:"<%=request.getContextPath()%>/user",
			success:function(data){
				if (data == 'success') {
					alert("删除用户成功！");
					window.location.reload();
				} else {
					alert(data);
				}
			},
			error:function () {
				alert("系统错误，请联系管理员。");
			}
		});
  	}
  </script>
  </head>
   <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>
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
	<td><a href="manager4.jsp"><font size="+2">报表输出</font></a></td>
</table>
</div>

<div class="a35"><font size="+1"><font size="+1">管理员：<%if (session.getAttribute("adminUser") != null){ %><%=((Admin)session.getAttribute("adminUser")).getAdmin_name() %><%} %></font>&nbsp;&nbsp;&nbsp;<a href="index">首页</a>&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/user?type=logout">登出</a></font></div>
<div class="a36"><font size="+3" color="#623131">用户管理</font></div>

<div class="a38" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a39" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a40" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a41" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a42"><table width="588" height="139" border="1" cellpadding="0" cellspacing="0" bordercolor="#9E9E9E">
  <tr>
    <td width="140"><font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名</font></td>
    <td width="140"><font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别</font></td>
    <td width="140"><font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;联系方式</font></td>
    <td width="140"><font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</font></td>
  </tr>
  <%List<User> userList = (List<User>) request.getAttribute("nowBookList");
  	if (userList != null ) {
  		for (User user:userList) {%>
  <tr>
    <td><%=user.getUser_name() %></td>
    <td><%=user.getSex() %></td>
    <td><%=user.getPhone() %></td>
    <td>&nbsp;<font size="+1">&nbsp;&nbsp;&nbsp;&nbsp;<a href="JavaScript:void(0)" onclick="delUser(<%=user.getUser_id() %>)">删除</a></font></td>
  </tr>
  <%	}
  	}%>
</table>
</div>
  </body>
</html>
