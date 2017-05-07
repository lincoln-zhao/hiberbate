<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/static/js/jquery-1.7.1.js"></script>
<script type="text/javascript">
	$(function() {
		type = '<%=request.getParameter("type")==null? 0 : request.getParameter("type") %>'; // type: 0:add, 1:update
		if (type != '0') {
			$("#user_id").attr("disabled",true);
			$("#user_id").val('<%=request.getParameter("user_id")%>');
			$("#user_name").val(decodeURI(decodeURI('<%=request.getParameter("user_name")%>')));
			$("#password").val(decodeURI(decodeURI('<%=request.getParameter("password")%>')));
		}
	})
</script>
</head>
<body>
<form action="javascript:viod(0)" id="addForm" method="post">
user id: <input name="user_id" id="user_id" type="text" /><br />
user name: <input name="user_name" id="user_name" type="text" /><br />
password: <input name="password" id="password" type="text" /><br />
<input type="submit" value="提交" onclick="formSubmit()">
<input type="reset" value="清空">
</form>
<script type="text/javascript">
function formSubmit() {
	var user_id = $("#user_id").val();
	var user_name = $("#user_name").val();
	var password = $("#password").val();
	if (type == '0') {
		$.ajax({
			type:'post',
			scriptCharset: 'utf-8',
			data:{user_id:user_id,user_name:user_name,password:password},
			url:"<%=request.getContextPath()%>/addUser",
			success:function(data){
				if (data == 'true') {
					alert("add success!");
				} else {
					alert("add fail!");
				}
			},
			error:function () {
				alert("error!!!");
			}
		});
	} else {
		$.ajax({
			type:'post',
			data:{user_id:user_id,user_name:user_name,password:password},
			url:"<%=request.getContextPath()%>/modifyUser",
			success:function(data){
				if (data == 'true') {
					alert("add success!");
				} else {
					alert("add fail!");
				}
			},
			error:function () {
				alert("error!!!");
			}
		});
	}
	window.returnValue = 1;
	window.close();
}
</script>
</body>
</html>