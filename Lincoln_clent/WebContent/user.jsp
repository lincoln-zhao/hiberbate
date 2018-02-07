<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/commen.js"></script>
</head>
<body>
	<script type="text/javascript">
	
		var url = "sand";
	
		$(function(){
			getList ();
		 });
		
		function getList () {
			
			var data = {"url":"/user/getAllUsers"};
			sandAjax(data,"getListBack");
		}
		
		function getListBack (data) {
			if (data.rt == 1) {
				data = data.data;
		    	for (var i=0;i<data.length;i++) {
		    		var innerHTML = "<tr>";
		    		innerHTML += "<td>"+ data[i].id +"</td>";
		    		innerHTML += "<td>"+ data[i].name +"</td>";
		    		innerHTML += "<td>"+ data[i].password +"</td>";
		    		innerHTML += "<td><a href='javascript:deleteUser(" + data[i].id + ")'>删除</a></td>";
		    		innerHTML += "</tr>";
		    		
		    		$('#userList').append(innerHTML);
		    	}
			}
		}
		
		function deleteUser (id) {
			alert(id);
			var userId = encodeURIComponent(encodeURIComponent(id));
			var data = {"url":"/user/deleteUser","param":"id="+userId};
			
			sandAjax(data,"deleteUserSuccess");
		}
		
		function deleteUserSuccess (data) {
			if (data.rt == 1) {
				alert('删除成功');
			} else {
				alert('删除失败');
			}
			window.location.reload();
		}
		
		function addUser () {
			var name = encodeURIComponent(encodeURIComponent($("#name").val()));
			var password = encodeURIComponent(encodeURIComponent($("#password").val()));
			var data = {"url":"/user/addUser","param":"name="+name+"&password="+password};

			sandAjax(data,"addUserSuccess");
		}
		
		function addUserSuccess (data) {
			if (data.rt == 1) {
				alert('添加成功');
			} else {
				alert('添加失败');
			}
			window.location.reload();
		}
		
		function updateUser () {
			var id = encodeURIComponent(encodeURIComponent($("#userId").val()));
			var name = encodeURIComponent(encodeURIComponent($("#userName").val()));
			var password = encodeURIComponent(encodeURIComponent($("#userPassword").val()));
			var data = {"url":"/user/updateUser","param":"id="+id+"&name="+name+"&password="+password};

			sandAjax(data,"updateUserSuccess");
		}
		
		function updateUserSuccess (data) {
			if (data.rt == 1) {
				alert('更新成功');
			} else {
				alert('更新失败');
			}
			window.location.reload();
		}
	</script>
	添加用户
	<form action="javascript:addUser()">
		name:<input type="text" id="name"><br>
		password:<input type="password" id="password"><br>
		<input type="submit" value="提交">
	</form>
	
	<br>
	<hr>
	<br>
	
	<a href="javascript:getList()">list</a>
	<table id='userList'>
		<tr>
			<td>id</td>
			<td>name</td>
			<td>password</td>
			<td>操作</td>
		</tr>
	</table>
	
	<br>
	<hr>
	<br>
	
	修改用户
	<form action="javascript:updateUser()">
		id:<input type="text" id="userId" disabled="disabled"><br>
		name:<input type="text" id="userName"><br>
		password:<input type="password" id="userPassword"><br>
		<input type="submit" value="提交">
	</form>
	

	
</body>
</html>