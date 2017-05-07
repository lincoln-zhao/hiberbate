<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>get all user</title>
<script src="<%=request.getContextPath()%>/static/js/jquery-1.7.1.js"></script>
<script type="text/javascript">
	$.ajax({
		type:'post',
		url:'<%=request.getContextPath()%>/getAllUser',
		dataType: 'json',
		success:function(data){
			json2page(data);
		},
		error:function () {
			alert("error!!!");
		}
	});
	
	function json2page (data) {
		var allUsers = eval(data);
		$("#allUsertable").append("");
		var tableBody = "<tr><th>id</th><th>name</th><th>password</th><th>操作</th></tr>";
		for (var i=0;i<allUsers[0].users.length;i++) {
			var param = "\"" + allUsers[0].users[i].user_id + "\", \"" + allUsers[0].users[i].user_name + "\", \"" + allUsers[0].users[i].password + "\"";
			tableBody += "<tr>";
			tableBody += "<td>" + allUsers[0].users[i].user_id + "</td>";
			tableBody += "<td>" + allUsers[0].users[i].user_name + "</td>";
			tableBody += "<td>" + allUsers[0].users[i].password + "</td>";
			tableBody += "<td><a href='javascript:void(0);' onclick='openModifyPage(" + param + ")'>update</a>&nbsp;&nbsp;&nbsp<a href='javascript:void(0);' onclick='deleteUser(" + param + ")'>delete</a></td>";
			tableBody += "</tr>";
		}
		$("#allUsertable").append(tableBody);
	}
	
	function openAddPage(){
		var k = window.showModalDialog('addUser.jsp','newwindow','width=200,height=200');
		if (k == 1) {
			window.location.reload();
		}
	}
	
	function openModifyPage(user_id, user_name, password){
		var param = "user_id=" + user_id + "&user_name=" + encodeURI(encodeURI(user_name)) + "&password=" + encodeURI(encodeURI(password));
		var k = window.showModalDialog('addUser.jsp?type=1&' + param,'newwindow','width=200,height=200');
		if (k == 1) {
			window.location.reload();
		}
	}
	
	function deleteUser(user_id, user_name, password){
		$.ajax({
			type:'post',
			url:'<%=request.getContextPath()%>/deleteUser',
			data:{user_id:user_id,user_name:user_name,password:password},
			success:function(data){
				if (data == 'true') {
					alert("delete success!");
				} else {
					alert("delete fail!");
				}
				window.location.reload();
			},
			error:function () {
				alert("error!!!");
				window.location.reload();
			}
		});

	}
</script>
</head>
<body>
<a href="javascript:void(0)" onclick="openAddPage()">add</a>
<br /><br />
	<table id="allUsertable" border="">

	</table>
</body>
</html>