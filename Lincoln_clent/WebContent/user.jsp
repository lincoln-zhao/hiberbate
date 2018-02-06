<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
</head>
<body>
	<script type="text/javascript">
	
		var url = "sand";
	
		$(function(){
			getList ();
		 });
		
		function getList () {
			
			var data = {"url":"/user/getAllUsers"};
			$.ajax({
			    type: "post",  
			    dataType: "json",  
			    async: "true",//将异步方法更改为同步执行  
			    url: url,  
			    data: data,  
			    success: function (obj) {
			    	alert(obj);
// 			    	for (var i=0;i<obj.length;i++) {
// 			    		var innerHTML = "<tr>";
// 			    		innerHTML += "<td>"+ obj[i].id +"</td>";
// 			    		innerHTML += "<td>"+ obj[i].name +"</td>";
// 			    		innerHTML += "<td>"+ obj[i].password +"</td>";
// 			    		innerHTML += "<td><a href='javascript:deleteUser(" + obj[i].id + ")'>删除</a></td>";
// 			    		innerHTML += "</tr>";
			    		
// 			    		$('#userList').append(innerHTML);
// 			    	}
			    	
			    },
			    error:function(obj){
				     alert("后台服务出现异常！");
			     }
			});
		}
		
		function deleteUser (id) {
			alert(id);
		}
		
		function addUser () {
			var name = encodeURIComponent(encodeURIComponent($("#name").val()));
			var password = encodeURIComponent(encodeURIComponent($("#password").val()));
			var data = {"url":"/user/addUser","param":"name="+name+"&password="+password};
			$.ajax({
			    type: "post",  
			    dataType: "json",  
			    async: "true",//将异步方法更改为同步执行  
			    url: url,  
			    data: data,  
			    success: function (obj) {
					if (obj == true) {
						alert('添加成功');
						window.location.reload()
					}
			    	
			    },
			    error:function(obj){
				     alert("后台服务出现异常！");
			     }
			});
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
	<form action="javascript:addUser()">
		id:<input type="text" id="userId" disabled="disabled"><br>
		name:<input type="text" id="userName"><br>
		password:<input type="password" id="password"><br>
		<input type="submit" value="提交">
	</form>
	

	
</body>
</html>