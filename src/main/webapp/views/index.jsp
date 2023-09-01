<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<%@ page session="false" %>

<h1 style="text-align: center;">Login Form</h1>

${ errorMessage }
${ Message }

<div class="position-absolute top-50 start-50 translate-middle">
<form action="/login" method="post">

<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">User ID</label>
  <input type="text" class="form-control" name="userid">
</div>
<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">Password</label>
  <input type="password" class="form-control" name="password">
</div>

<a href="/registrationPage">User Registration</a>

<br>
<br>

<button type="submit" class="btn btn-primary">Submit</button>

</form>
</div>


</body>
</html>