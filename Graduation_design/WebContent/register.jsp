<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  <link rel="stylesheet" href="css/regist.css" type="text/css"></link>
   <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>
  
  <script src="css/jquery-1.9.0.min.js"></script>
  <script type="text/javascript">
  	function register() {
  		if(!$('#radio1').is(':checked')) {
  		    alert("请确认同意注册协议！");
  		    return false;
  		}
  		
  		var password = $("#password").val();
  		var password2 = $("#password2").val();
  		
  		if (password != password2) {
  			alert("两次输入密码不一致！");
  			return false;
  		}
  		
  		$("#registerForm").submit();
  		
  		var userName = $("#userName").val();
  		var sex = $("input[name='sex']:checked").val();
  		var phone = $("#phone").val();
  		
  		$.ajax({
			type:'post',
			data:{userName:userName,password:password,sex:sex,phone:phone,type:"register"},
			url:"<%=request.getContextPath()%>/user",
			success:function(data){
				if (data == 'success') {
					alert("注册成功！");
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
<div class="a20"><font size="+1"><a href="">首页</a>&nbsp;&nbsp;&nbsp;<a href="">返回</a></font> </div>

<div class="a21"><img src="img/regist.a.png" width="1302" height="462" ></img></div>

<div class="a22" style="border:solid; border-color:#000000; border-width:medium"></div>

<div class="a23"><font size="+2" color="#000000">新用户注册</font></div>

<div class="a24">

  <table width="330" height="236" border="0">
  <tr>
    <td width="103" height="42">&nbsp;</td>
    <td width="217">&nbsp;</td>
  </tr>
  <tr>
    <td height="36"> &nbsp;&nbsp;&nbsp;&nbsp;用户名：</td>
    <td><input  type="text" name="userName" id="userName" height="20px;" width="200px;" /></td>
  </tr>
  <tr>
    <td height="36">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 密码：</td>
    <td><input  type="password" name="password" id="password"  height="20px;" width="200px;" /></td>
  </tr>
  <tr>
    <td height="36">&nbsp;&nbsp;密码确认：</td>
    <td><input  type="password" name="password2" id="password2"  height="20px;" width="200px;" /></td>
  </tr>
  <tr>
    <td height="36">&nbsp;&nbsp;联系方式：</td>
    <td><input  type="text" name="phone" id="phone"  height="20px;" width="200px;"/></td>
  </tr>
  <tr>
    <td height="36">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 性别：</td>
    <td><input name="sex" type="radio" value="男" />男
	<input name="sex" type="radio" value="女" />女
	<div class="a25">
	  <input id="radio1" type="checkbox" name="agree">
	同意<a href="">《图书馆用户注册协议》</a></div>
	<div class="a26">
	  <input name="Input" type="submit" style="height:30px; width:100px;" value="注册" onclick="register()"></div></td>
	
  </tr>
  
</table>

</div>
  </body>
</html>
