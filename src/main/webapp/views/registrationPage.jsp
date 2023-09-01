<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registeration</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>
<body>

	<h1 style="text-align: center;">Registration Form</h1>

	<div class="position-absolute top-50 start-50 translate-middle">
		<form action="register" method="post">

			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">User
					ID</label> <input type="text" class="form-control" name="userid">
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Name</label>
				<input type="text" class="form-control" name="name">
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Email</label>
				<input type="text" class="form-control" name="email">
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Password</label>
				<input type="password" class="form-control" name="password">
			</div>
			
			<input type="hidden" id="attemptsField" name="attempts" value="0">

			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="role"
					value="admin" id="flexRadioDefault1"> <label
					class="form-check-label" for="flexRadioDefault1"> Admin </label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="role"
					value="student" id="flexRadioDefault2"> <label
					class="form-check-label" for="flexRadioDefault2"> Student </label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="role"
					value="teacher" id="flexRadioDefault2"> <label
					class="form-check-label" for="flexRadioDefault2"> Teacher </label>
			</div>

			<br> <br> <a href="/">User Login</a> <br> <br>

			<button type="submit" class="btn btn-primary">Submit</button>

		</form>
	</div>

</body>
</html>