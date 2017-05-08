<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userlogin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  <link rel="stylesheet" href="css/userlogin.css" type="text/css"></link>
  <script src="css/jquery-1.9.0.min.js"></script>
  <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>
  <script type="text/javascript">
  	function userLogin () {
  		var username = $("#username").val();
  		var password = $("#password").val();

		$.ajax({
			type:'post',
			data:{userName:username,password:password,type:"login"},
			url:"<%=request.getContextPath()%>/user",
			success:function(data){
				if (data == 'success') {
					window.returnValue = 1;
					window.close();
				} else {
					alert("用户名或密码错误");
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
<div class="a13" align="center">
  <table width="508" heigh="258" border="0" >
<tr>
<td  height="31"></td>
<td></td>
</tr>
<tr>
    <td width="127" height="65"> <font size="+1" color="#000000">&nbsp;&nbsp;&nbsp;&nbsp;用户名: </font> </td>
    <td width="371"> <input name="username" id="username" type="text" style="height:30px; width:300px;"></td>
  </tr>
  <tr>
    <td height="70"><font size="+1" color="#000000">&nbsp;&nbsp;&nbsp; &nbsp;密码:</font></td>
    <td> <input name="password" id="password" type="text" style="height:30px; width:300px;">
	  <div class="a14">
	    <a href="javascript:void(0)" onclick="userLogin()"><input  type="submit"  style="height:40px; width:100px;" name="login" value="登录" /></a>&nbsp;&nbsp;&nbsp;
  <a href="regist.jsp"><input name="" type="button" style="height:40px; width:100px;" value="注册"></a></div>  </td></tr>
</table>
</div>
<p>&nbsp;</p>
  </body>
</html>
