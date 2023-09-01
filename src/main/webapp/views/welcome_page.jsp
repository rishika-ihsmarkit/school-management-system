<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.osttra.to.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	<%
	User user = (User) session.getAttribute("user");
	List<User> userList = (List<User>) session.getAttribute("userList");
	%>


	<h1 style="margin-left: 450px; margin-top: 50px; margin-bottom: 100px;">Osttra School Management System</h1>
		
		${ updateSuccessMsg } 
		

	<div class="container">

		<table class="table">
			<thead>
				<tr>
					<th scope="col">User ID</th>
					<th scope="col">Name</th>
					<th scope="col">Email</th>
					<th scope="col">Role</th>
					<th scope="col">Status</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${ user.getUserid() }</td>
					<td>${ user.getName() }</td>
					<td>${ user.getEmail() }</td>
					<td>${ user.getRole() }</td>
					<td>${ user.getStatus() }</td>
				</tr>
			</tbody>
		</table>

		<a class="btn btn-warning" href="/updatePage/${user.getUserid()}" role="button">Update Details</a>
		<a class="btn btn-danger" href="/delete/${user.getUserid()}" role="button">Delete</a>
		<a class="btn btn-dark" href="/logout/${user.getUserid()}" role="button">Logout</a>
		
		
		<% if(user.getRole().equals("admin") && userList!=null) {%>
		
		<h3 style="margin-top: 70px; margin-bottom: 20px;">User details</h3>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">User ID</th>
					<th scope="col">Name</th>
					<th scope="col">Email</th>
					<th scope="col">Role</th>
					<th scope="col">Status</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>

				<%for (int i = 0; i < userList.size(); i++){ %>

				<tr>
					<td><%= userList.get(i).getUserid()%></td>
					<td><%=userList.get(i).getName()%></td>
					<td><%=userList.get(i).getEmail()%></td>
					<td><%=userList.get(i).getRole()%></td>
					<td><%=userList.get(i).getStatus()%></td>
					<td>
						<div class="btn-group" role="group" aria-label="Basic mixed styles example">
							<a href="/deleteUser/<%= userList.get(i).getUserid()%>" class="btn btn-danger">Delete</a>
							<a href="/updatePage/<%= userList.get(i).getUserid()%>" class="btn btn-warning">Update</a>
							<% if(userList.get(i).getStatus().equals("active")){ %>
							<a href="/block/<%= userList.get(i).getUserid()%>" class="btn btn-success">Block</a>
							<%}else{ %>
							<a href="/activate/<%= userList.get(i).getUserid()%>" class="btn btn-success">Activate</a>
							<% } %>
						</div>
					</td>

				</tr>

				<%}%>
			</tbody>
		</table>
		
		<% } %>
		
	</div>

</body>
</html>