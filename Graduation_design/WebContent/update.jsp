<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.ysu.entity.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  <link rel="stylesheet" href="css/update.css" type="text/css"></link>
   <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>
  
  <script src="css/jquery-1.9.0.min.js"></script>
  <script type="text/javascript">
  	<%
  		User user = (User)session.getAttribute("loginUser");
  		if (user != null) {
  	%>
	$(function () {
  		var param = window.dialogArguments;
  		$("#userId").val('<%=user.getUser_id() %>');
  		$("#userName").val('<%=user.getUser_name() %>');
  		$("#password").val('<%=user.getPassword() %>');
  		$("#password2").val('<%=user.getPassword() %>');
  		if ('<%=user.getSex() %>' == '男') {
  			$("#s1").prop('checked',true);
  		} else {
  			$("#s2").prop('checked',true);
  		}
  		$("#phone").val('<%=user.getPhone() %>');
  	});
  	<%} else { %>
  		alert("用户未登陆！");
  		window.location.href = "<%=request.getContextPath()%>/main.jsp";
  	<%} %>
  	
  	function modify () {
  		var password = $("#password").val();
  		var password2 = $("#password2").val();
  		
  		if (password != password2) {
  			alert("两次输入密码不一致！");
  			return false;
  		}
  		
  		var userId = $("#userId").val();
  		var userName = $("#userName").val();
  		var sex = $("input[name='sex']:checked").val();
  		var phone = $("#phone").val();
  		
  		$.ajax({
			type:'post',
			data:{userId:userId,userName:userName,password:password,sex:sex,phone:phone,type:"modifyUser"},
			url:"<%=request.getContextPath()%>/user",
			success:function(data){
				if (data == 'success') {
					alert("修改成功！");
					window.location.href = "<%=request.getContextPath()%>/main.jsp"
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
<div class="a17">
  <div class="a18"><img src="img/main.c.png" width="129" height="112" /></div>
  
  <div class="a19">
    <p><font size="+3" color="#000066"> &nbsp;燕山大学里仁学院图书馆</font></p>
    <p><font size="+1" color="#000066">Benevolence in yanshan university college library</font></p>
  </div>
</div>
<div class="a20"><font size="+1"><a href="main.jsp">首页</a>&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1)">返回</a></font> </div>

<div class="a21"><img src="img/regist.a.png" width="1302" height="462"></img></div>

<div class="a22" style="border:solid; border-color:#000000; border-width:medium"></div>

<div class="a23"><font color="#000000" size="+2">用户信息修改</font></div>

<div class="a24">
  <table width="330" height="236" border="0">
  <tr>
    <td width="103" height="42">&nbsp;</td>
    <td width="217">&nbsp;</td>
    <input  type="hidden" name="userId" id="userId" /><!-- 隐藏userId -->
  </tr>
  <tr>
    <td height="36"> &nbsp;&nbsp;&nbsp;&nbsp;用户名：</td>
    <td><input  type="text" name="id" id="userName" height="20px;" width="200px;" /></td>
  </tr>
  <tr>
    <td height="36">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 密码：</td>
    <td><input  type="text" name="id" id="password"  height="20px;" width="200px;" /></td>
  </tr>
  <tr>
    <td height="36">&nbsp;&nbsp;密码确认：</td>
    <td><input  type="text" name="id" id="password2"  height="20px;" width="200px;" /></td>
  </tr>
  <tr>
    <td height="36">&nbsp;&nbsp;联系方式：</td>
    <td><input  type="text" name="id" id="phone" height="20px;" width="200px;"/></td>
  </tr>
  <tr>
    <td height="36">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 性别：</td>
    <td><input name="sex" id="s1" type="radio" value="男" />男
	<input name="sex" id="s2" type="radio" value="女" />女
	<div class="a26">
	  <input name="Input" type="submit" style="height:30px; width:100px;" value="修改" onclick="modify()"></div></td>
	
  </tr>
  
</table>
</div>

  </body>
</html>
