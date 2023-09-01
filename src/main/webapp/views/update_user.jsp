<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%@ page import="com.osttra.to.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>
<body>

<% User user = (User) session.getAttribute("user"); %>


	<h1 style="text-align: center;">Update User</h1>

	<div class="position-absolute top-50 start-50 translate-middle">
		<form action="/update" method="post">

			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">User ID</label> 
				<input type="text" class="form-control" name="userid" value="${deletingUser.getUserid() }" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Name</label>
				<input type="text" class="form-control" name="name" value="${deletingUser.getName() }">
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Email</label> 
				<input type="text" class="form-control" name="email" value="${deletingUser.getEmail() }">
			</div>
			
			<input type="hidden" id="attemptsField" name="attempts" value="${deletingUser.getAttempts() }">
			
			<br>

			<a href="/home" class="btn btn-success">Home</a>

		    <br> <br>

			<button type="submit" class="btn btn-primary">Submit</button>

		</form>
	</div>


</body>
</html>