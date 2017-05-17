<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
  	$(function () {
  		var param = window.dialogArguments;
  		$("#bookId").val(param.bookid);
  		$("#bookName").val(param.bookName);
  		$("#author").val(param.author);
  		$("#classification").val(param.classification);
  		$("#position").val(param.position);
  	});
  	
  	function modifyBook() {
  		var bookId = $("#bookId").val();
  		var bookName = $("#bookName").val();
  		var author = $("#author").val();
  		var classification = $("#classification option:selected").val();
  		var position = $("#position").val();
  		
		$.ajax({
			type:'post',
			data:{bookId:bookId,bookName:bookName,author:author,classification:classification,position:position,type:"modifyBook"},
			url:"<%=request.getContextPath()%>/book",
			success:function(data){
				if (data == 'success') {
					alert("图书修改成功！");
					window.location.reload();
				} else {
					alert("系统错误，请联系管理员。");
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
<div class="a44" align="center"><font size="+2">添加图书</font></div>

<div class="a47">
  <table width="857" height="76" border="0">
  <tr>
    <td width="29" height="36">&nbsp;&nbsp;图书号：</td>
    <td width="192"><input name="id" id = "bookId" type="text" /></td>
  </tr>
  <tr>
    <td width="29">&nbsp;&nbsp;图书名：</td>
    <td width="192"><input name="id" id="bookName" type="text" /></td>
  </tr>
	<td width="29">图书作者：</td>
    <td width="196"><input name="id" id="author" type="text" /></td>
  </tr>
  <tr>
    <td width="29">图书分类：</td>
    <td>
	    <select name="classification" id="classification" style="width: 151.24px;"> 
			<option value="文学">文学</option> 
			<option value="小说">小说</option> 
			<option value="技术">技术</option> 
		</select> 
	</td>
  </tr>
  <tr>
    <td width="29">图书位置：</td>
    <td><input name="id" id="position" type="text" /></td>
  </tr>
</table>
</div>


<div class="a48">
<input  type="submit"  style="height:30px; width:80px;" name="upload" value="修改" onclick="modifyBook()" /></div>
  </body>
</html>
