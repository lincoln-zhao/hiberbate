<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

  <link rel="stylesheet" href="css/personal.css" type="text/css"></link></head>
   <style>
  a{text-decoration:none}
  a:hover{text-decoration:underline}
  </style>
  <body>
<div class="a17">
  <div class="a18"><img src="img/main.c.png" width="129" height="112" /></div>
  
  <div class="a19">
    <p><font size="+3" color="#000066"> &nbsp;燕山大学里仁学院图书馆</font></p>
    <p><font size="+1" color="#000066">Benevolence in yanshan university college library</font></p>
  </div>
</div>
<div class="a20"><font size="+1"><a href="main.jsp">首页</a>&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1)">返回</a></font> </div>

<div class="a27" style="border-color:#000033; border-right-style:solid;border-left-style:solid; border-width:medium;">
  <p><font size="+3"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 个人信息</font></p>
  <p><font size="+3">&nbsp;&nbsp;用户名：</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="update.jsp"><font size="+1">修改个人信息</font></a></p>
</div>

<div class="a28"></div>

<div class="a29" style="border-color:#000033; border-right-style:solid;border-top-style:solid;border-left-style:solid; border-width:medium;">
  <p><font size="+3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 图书推送</font></p>
  <p>&nbsp;</p>
</div>

<div class="a32"><table width="361" height="294" border="1">
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</div>
<div class="a30" align="center"><font size="+3">借书情况</font></div>
<div class="a31">
  <table width="855" height="159" border="1" align="center">
  <tr>
    <td width="163"><font size="+3"> &nbsp;图书编号</font></td>
    <td width="163"><font size="+3">&nbsp;&nbsp;图书名</font></td>
    <td width="163"><font size="+3">&nbsp;借书时间</font></td>
    <td width="166"><font size="+3">&nbsp;还书时间</font></td>
    <td width="166"><font size="+3">&nbsp;借书状态</font></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</div>
  </body>
</html>
