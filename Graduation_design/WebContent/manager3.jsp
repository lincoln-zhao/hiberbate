<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manager3.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  <link rel="stylesheet" href="css/manager3.css" type="text/css"></link></head>
   <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>
  <body>
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
    <td><a href="manager2.jsp"><font size="+2">借还书管理</font></a></td>
  </tr>
  <tr>
    <td width="44" height="65"><img src="img/manager.a.png" width="32" height="29" /></td>
    <td><a href="manager3.jsp"><font size="+2">图书管理</font></a></td>
  </tr>
  <tr>
    <td width="44" height="65"><img src="img/manager.a.png" width="32" height="29" /></td>
	<td><a href="manager4.jsp"><font size="+2">报表输出</font></a></td>
</table>
</div>

<div class="a35"></div>

<div class="a36"><font color="#623131" size="+2">图书管理</font></div>

<div class="a38" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a39" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a40" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a41" style="border-color:#000033; border-top-style:solid;border-width:thin;"></div>

<div class="a44" align="center"><font size="+2">添加图书</font></div>

<div class="a47">
  <table width="857" height="76" border="0">
  <tr>
    <td width="79" height="36">&nbsp;&nbsp;图书号：</td>
    <td width="192"><input name="id" type="text" /></td>
    <td width="79">&nbsp;&nbsp;图书名：</td>
    <td width="192"><input name="id" type="text" /></td>
	<td width="79">图书作者：</td>
    <td width="196"><input name="id" type="text" /></td>
  </tr>
  <tr>
    <td>图书分类：</td>
    <td><input name="id" type="text" /></td>
	<td>图书数量：</td>
    <td><input name="id" type="text" /></td>
    <td>图书位置：</td>
    <td><input name="id" type="text" /></td>
  </tr>
</table>
</div>


<div class="a48">
<input  type="submit"  style="height:30px; width:80px;" name="upload" value="上传" /></div>
<div class="a45" align="center"><font size="+2">图书信息列表</font></div>

<div class="a46">
  <table width="858" height="156" border="1" cellpadding="0" cellspacing="0">
  <tr>
    <td width="100">&nbsp;&nbsp;&nbsp;图书号</td>
    <td width="100">&nbsp;&nbsp;&nbsp;图书名</td>
    <td width="100">&nbsp;&nbsp;&nbsp;图书作者</td>
    <td width="100">&nbsp;&nbsp;&nbsp;图书分类</td>
    <td width="100">&nbsp;&nbsp;&nbsp;剩余数量</td>
	<td width="100">&nbsp;&nbsp;&nbsp;图书位置</td>
	<td width="100">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</td>
  </tr>
  <tr>
	<td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
	<td>&nbsp;</td>
    <td>&nbsp;<font size="+1">&nbsp;<a href="">修改</a></font>&nbsp;&nbsp;<font size="+1"><a href="">删除</a></font></td>
  </tr>
</table>
</div>

<div class="a49" style="border-color:#000033; border-right-style:solid;border-width:medium;"></div>
  </body>
</html>
