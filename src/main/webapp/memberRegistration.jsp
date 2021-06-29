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

		#errorBlock {
			color: red;
		}
	</style>
	<meta charset="ISO-8859-1">
	<title>Register</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<br>
		<h3>Registration Form</h3>
		<form onsubmit=register()>
			<div class="form">
				<label for="name">
					<strong>Enter your Name: </strong>
					<span class="required"> *</span>
				</label>
				<input type="text" id="name" name="name" minlength="3" maxlength="20" required autofocus placeholder="Enter your name">
			</div>
			<br />
			<div class="form">
				<label for="phoneNo">
					<strong>Enter your Phone No.
					</strong>
					<span class="required"> *</span>
				</label>
				<input type="tel" id="phoneNo" name="phoneNo" required autofocus placeholder="Enter Phone number">

			</div>
			<br />
			<div class="form">
				<label for="address">
					<strong>Enter Residential Address </strong>
					<span class="required"> *</span>
				</label>
				<textarea id="address" minlength="10" maxlength="200" name="address" required autocomplete="on" autofocus placeholder="Enter Address">
				</textarea>
			</div>
			<br />

			<div class="form">
				<label for="emailId">
					<strong>Enter E-mail ID </strong>
					<span class="required"> *</span>
				</label>
				<input type="email" id="emailId" name="emailId" required autofocus placeholder="Enter Email ID">
			</div>
			<br/>


			<div class="form">
				<label for="role">
					<strong>Select your role </strong>
					<span class="required"> *</span>
				</label>
				<select name="role" id="role">
					<option value="SELLER">Seller</option>
					<option value="BUYER">Buyer</option>
					<option value="ADMIN">Admin</option>
				</select>
			</div>
			<br />

			<div class="form">
				<label for="password">
					<strong>Enter Password </strong>
					<span class="required"> *</span>
				</label>
				<input type="password" id="password" name="password" minlength="7" required autofocus placeholder="Enter Password">
			</div>
			<br />

			<button class="btn btn-primary">Register</button>

			<button type="reset" class="btn btn-danger">Reset</button>

		</form>

		<br />
		<p id="errorBlock"></p>
		<br />

		<script type="text/javascript">
			function register() {
				event.preventDefault();
				let name = document.querySelector("#name").value;
				let phoneNo = document.querySelector("#phoneNo").value;
				let address = document.querySelector("#address").value;
				let emailId = document.querySelector("#emailId").value;
				let role = document.querySelector("#role").value;
				let password = document.querySelector("#password").value;


				let member = {
					"name": name,
					"phoneNo": phoneNo,
					"address": address,
					"email": emailId,
					"role": role,
					"password": password
				};

				console.log(member);
				let url = "motorcycleapp/v1/auth/member/registration";
				content = "";
				axios.post(url, member).then(res => {
					let data = res.data;
					console.log(data);
					alert("Sucessfully Registered");
					window.location.href = "index.jsp";

				}).catch(err => {

					let data = err.response.data;

					if (data.errorMessage != null) {
						console.log("Exception:" + data.errorMessage);
						document.querySelector("#errorBlock").innerHTML = "Unable to Register";
					}
					else if (data.errors != null) {
						let content = "<ul>";
						for (let field in data.errors) {
							let msg = data.errors[field];
							content += "<li>" + msg + "</li>";
						}
						content += "</ul>";
						console.log(content);
						document.querySelector("#errorBlock").innerHTML = content;
					}
					else {
						content = "Unable to Register";
						console.log(content);
						document.querySelector("#errorBlock").innerHTML = content;
						alert(content);
					}

				})
			}
		</script>
	</main>
</body>

</html>