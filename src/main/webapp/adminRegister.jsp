<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

	<!DOCTYPE html>
	<html lang="en">

	<head>
		<style>
			.required {
				color: red;
			}

			div .form {
				margin-bottom: 50px;
			}

			label {
				display: inline-block;
				width: 250px;
				color: #000000;
			}
		</style>
		<meta charset="ISO-8859-1">
		<title>Register</title>
	</head>

	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<main class="container-fluid">

			<br>
			<h3>Admin Registration</h3>

			<form onsubmit=register()>
				<div class="form">
					<label for="name">
						<strong>Enter your Name </strong>
						<span class="required"> *</span>
					</label>
					<input type="text" id="name" name="name" minlength="2" maxlength="20" required autofocus placeholder="Enter your name">
					<br>
				</div>
				<div class="form">
					<label for="id">
						<strong>Enter your ID </strong>
						<span class="required"> *</span>
					</label>
					<input type="text" id="id" name="id" required autofocus placeholder="Enter your ID">
					<br>

				</div>
				<div class="form">
					<label for="pass">
						<strong>Enter Password </strong>
						<span class="required"> *</span>
					</label>
					<input type="password" id="pass" name="pass" required autofocus placeholder="Enter Password">
					<br>
					<br>
					<br>
				</div>
				<button class="btn btn-primary">Register</button>

				<button type="reset" class="btn btn-danger">Reset</button>

			</form>

			<p id="message"></p>
			<br/>
			<br/>
			<ul>
				<li> Name must be of length 2 to 20 characters. No numbers</li>
				<li>Id must be of length 2 to 15 character. No special characters.</li>
				<li>Password must have minimum 7 characters</li>
			</ul>
			<script type="text/javascript">
				function register() {
					event.preventDefault();
					let name = document.querySelector("#name").value;
					let id = document.querySelector("#id").value;
					let key = document.querySelector("#pass").value;

					let admin = {
						"name": name,
						"adminId": id,
						"password": key
					};
					console.log(admin);
					let url = "motorcycleapp/v1/auth/admin/registration";
					content = "";
					axios.post(url, admin).then(res => {
						let data = res.data;
						console.log(data.infoMessage);
						document.querySelector("#message").innerHTML = data.infoMessage;
						alert(data.infoMessage);
						//window.location.href = "";
					}).catch(err => {
						console.log("Error");
						let data = err.response.data;
						console.log(data.errorMessage);
						alert(data.errorMessage);
						content += data.errorMessage;
						document.querySelector("#message").innerHTML = content;
					})
				}
			</script>
		</main>
	</body>

	</html>