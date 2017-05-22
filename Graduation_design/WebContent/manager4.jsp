<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.ysu.entity.Classification" %>
<%@page import="com.ysu.entity.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manager4.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="css/manager3.css" type="text/css"></link>
  <link rel="stylesheet" href="css/manager4.css" type="text/css"></link>
   <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>
  
  <script src="css/jquery-1.9.0.min.js"></script>
  <script type="text/javascript">
  <%if (session.getAttribute("adminUser") == null){ %>
  	alert("管理员未登录！");
  	window.location.href = "<%=request.getContextPath()%>/main.jsp";
  <%}%>
  
  function addClassification () {
	  var name = $("#cName").val();
		$.ajax({
			type:'post',
			data:{c_name:name,type:"addClassification"},
			url:"<%=request.getContextPath()%>/book",
			success:function(data){
				if (data == 'success') {
					alert("添加成功！");
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
  
  function delClassification (id) {
		$.ajax({
			type:'post',
			data:{c_id:id,type:"delClassification"},
			url:"<%=request.getContextPath()%>/book",
			success:function(data){
				if (data == 'success') {
					alert("删除成功！");
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

<div class="a35"><font size="+1"><font size="+1">管理员：<%if (session.getAttribute("adminUser") != null){ %><%=((Admin)session.getAttribute("adminUser")).getAdmin_name() %><%} %></font>&nbsp;&nbsp;&nbsp;<a href="index">首页</a>&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/user?type=logout">登出</a></font></div>

<div class="a36"><font size="+2" color="#623131"></font></div>

<div class="a38" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a39" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a40" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a41" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a44" align="center"><font size="+2">分类管理</font></div>

<div class="a47">
  <table width="857" height="76" border="0">
  <tr>
    <td width="79" height="36">&nbsp;&nbsp;分类名：</td>
    <td width="192"><input name="id" id = "cName" type="text" /></td>
  </tr>
</table>
</div>
<div class="a48">
<input  type="submit"  style="height:30px; width:80px;" name="upload" value="添加" onclick="addClassification()" /></div>
<div class="a45" align="center"><font size="+2">分类信息列表</font></div>

<div class="a46">
  <table width="858" height="156" border="1" cellpadding="0" cellspacing="0">
  <tr>
    <td width="100">&nbsp;&nbsp;&nbsp;分类名</td>
	<td width="100">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</td>
  </tr>
  <%if (request.getAttribute("classificationList") != null) { 
	  List<Classification> classificationList = (List<Classification>) request.getAttribute("classificationList");
	  for (Classification classification:classificationList) {
  %>
	  <tr>

	    <td><%=classification.getClassificationName() %></td>

	    <td>&nbsp;&nbsp;
	    	<font size="+1"><a href="javascript:void(0)" onclick="delClassification(<%=classification.getClassificationId() %>)">删除</a></font>
	    </td>
	  </tr>
  <%	}
	 } %>
</table>
</div>
  </body>
</html>
