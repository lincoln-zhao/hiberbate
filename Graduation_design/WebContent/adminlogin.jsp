<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adminlogin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  <link rel="stylesheet" href="css/adminlogin.css" type="text/css"></link></head>
    <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>
  <body>
 <div class="a1">
  <div class="a2"><img src="img/main.c.png" width="125" height="128"></img></div>
  <div class="a3">
    <p><font  color="#FFFFFF" size="+3"> &nbsp; 燕山大学里仁学院图书馆</font></p>
    <p><font color="#FFFFFF" size="+1">Benevolence in yanshan university college library</font></p>
  </div>
  
  <div class="a4">
  <font color="#FFFFFF">
  <h3>登录&nbsp;&nbsp;管理员登录</h3>
  </font></div>
</div>

<div class="a7"><font color="#FFFFFF">
<h3>首页&nbsp;&nbsp;排行榜&nbsp;&nbsp;服务&nbsp;&nbsp;概况</h3></font></div>
<div class="a5"><img src="img/main.a.jpg" width="935" height="282"></img></div>

<div class="a6">
  <h1>
    <input name="搜索" type="text"  style="width:300px; height:30px;"/>
    &nbsp;
  <input type="submit" name="Submit" value="查找"  style="width:60px; height:30px; color:#FFFFFF; background-color:#FF0000;"/>
  </h1>
</div>

<div class="a8" align="center"><font color="#0033CC" size="+3">图书推送</font><table width="310" border="0">
  <tr>
    <td width="104"><img src="img/main.f.png"width="82" height="95"></img>&nbsp;</td>
    <td width="190">&nbsp;天才在左 疯子在右</td>
  </tr>
  <tr>
    <td height="98"><img src="img/main.g.png"width="67" height="88"></img>&nbsp;</td>
    <td>&nbsp;菜根谭</td>
  </tr>
  <tr>
    <td height="110"><img src="img/main.h.png"width="71" height="95"/></img>&nbsp;</td>
    <td>&nbsp;WEB架构</td>
  </tr>
</table>


</div>
<div class="a9" align="center"><font color="#0033CC" size="+3">热门借阅</font>
  <table width="308" height="315" border="0">
  <tr>
    <td width="298">&nbsp;<img src="img/main.e.png"width="21" height="17"></img>&nbsp;狼图腾</td>
  </tr>
  <tr>
    <td>&nbsp;<img src="img/main.e.png"width="21" height="17"></img>&nbsp;天才在左 疯子在右</td>
  </tr>
  <tr>
    <td>&nbsp;<img src="img/main.e.png"width="21" height="17"></img>&nbsp;C语言程序设计</td>
  </tr>
  <tr>
    <td>&nbsp;<img src="img/main.e.png"width="21" height="17"></img>&nbsp;谁动了我的奶酪</td>
  </tr>
  <tr>
    <td>&nbsp;<img src="img/main.e.png"width="21" height="17"></img>&nbsp;明朝那些事儿></td>
  </tr>
  <tr>
    <td>&nbsp;<img src="img/main.e.png"width="21" height="17"></img>&nbsp;平凡的世界</td>
  </tr>
  <tr>
    <td height="45">&nbsp;<img src="img/main.e.png"width="21" height="17"></img>&nbsp;西点军校</td>
  </tr>
</table></div>

<div class="a10" align="center" ><font color="#0033CC" size="+3">最新动态</font>
  <table width="309" height="316" border="0">
  <tr>
    <td width="299">&nbsp;<img src="img/main.e.png"width="21" height="17"><font size="+1">&nbsp;新书入馆</font></td>
  </tr>
  <tr>
    <td>&nbsp;<img src="img/main.e.png"width="21" height="17"><font size="+1" >&nbsp;读书节活动</font></td>
  </tr>
  <tr>
    <td>&nbsp;<img src="img/main.e.png"width="21" height="17"><font size="+1" >&nbsp;提供两会主题文献通知</font></td>
  </tr>
  <tr>
    <td>&nbsp;<img src="img/main.e.png"width="21" height="17"><font size="+1" >&nbsp;暑期开馆安排</font></td>
  </tr>
  <tr>
    <td>&nbsp;<img src="img/main.e.png"width="21" height="17"><font size="+1" >&nbsp;毕业季图书</font></td>
  </tr>
  <tr>
    <td>&nbsp;<img src="img/main.e.png"width="21" height="17"><font size="+1" >&nbsp;清明开馆安排</font></td>
  </tr>
  <tr>
    <td height="44">&nbsp;<img src="img/main.e.png"width="21" height="17"><font size="+1" >&nbsp;关于借还书通知</font></td>
  </tr>
</table></div>
<div class="a50"><a href=""><font color="#999999">文学类</font></a>&nbsp;&nbsp;<a href=""><font color="#999999">专业类</font></a></div>
<div class="a11" align="center"><br>
<font size="4">燕山大学里仁学院</font><br><br>
<font size="5">版权所有©翻版必究</font></div>

<div class="a15" style="border-style:solid; border-width:medium; border-color:#000066"></div>
<div class="a12">
<font size="+1" color="#000000">管理员登录</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="main.jsp"> <font size="+2" color="#FF0000"><strong>×</strong></font></a>
</div>

<div class="a13" align="center">
  <table width="508" heigh="258" border="0" >
<tr>
<td  height="31"></td>
<td></td>
</tr>
<tr>
    <td width="127" height="65"> <font size="+1" color="#000000">&nbsp;&nbsp;&nbsp;&nbsp;用户名: </font> </td>
    <td width="371"> <input name="username" type="text" style="height:30px; width:300px;"></td>
  </tr>
  <tr>
    <td height="70"><font size="+1" color="#000000">&nbsp;&nbsp;&nbsp; &nbsp;密码:</font></td>
    <td> <input name="password" type="text" style="height:30px; width:300px;">
	  <div class="a14">
	    <input  type="submit"  style="height:40px; width:100px;" name="login" value="登录" /></div>  </td></tr>
</table>
</div>
<p>&nbsp;</p>

  </body>
</html>
