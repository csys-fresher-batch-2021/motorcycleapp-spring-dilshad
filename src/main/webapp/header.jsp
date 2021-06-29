<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">
<link rel="stylesheet" href="assets/css/style.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script src="assets/js/axios.min.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<%
String loggedInUsername = (String) session.getAttribute("LOGGED_IN_ADMIN");
%>


<header>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<a class="navbar-brand" href="index.jsp">Motorcycle</a>
		<button class="navbar-toggler d-lg-none" type="button"
			data-toggle="collapse" data-target="#collapsibleNavId"
			aria-controls="collapsibleNavId" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavId">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home <span class="sr-only">(current)</span></a></li>

				<li class="nav-item"><a class="nav-link"
					href="DisplayBikesServlet">Show Room</a></li>

				<li class="nav-item"><a class="nav-link"
					href="displayByPlateNoJSON.jsp">Buy</a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="dropdownId"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Search
						by</a>
					<div class="dropdown-menu" aria-labelledby="dropdownId">
						<a class="dropdown-item" href="findBikeByModel.jsp">Bike model</a>
						<a class="dropdown-item" href="shortlistByPrice.jsp">Price
							limit</a>
					</div></li>

				<%
				if (loggedInUsername != null) {
				%>
				<li class="nav-item"><a class="nav-link" href="addBike.jsp">Add
						bike</a></li>

				<li class="nav-item"><a class="nav-link"
					href="GetUnverifiedBikeServlet">Confirm upload</a></li>
				<li class="nav-item"><a class="nav-link" href="removeBike.jsp">Remove</a></li>
				<li class="nav-item"><a class="nav-link"
					href="updateBikeDetails.jsp">Update</a></li>
				<%
				}
				%>

			</ul>
			<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
				<%
				if (loggedInUsername == null) {
				%>
				<li class="nav-item active"><a class="nav-link"
					href="loginPage.jsp">Admin login</a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link" href="#">Welcome <%=loggedInUsername%></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a>
				</li>
				<%
				}
				%>
			</ul>
		</div>
	</nav>
</header>