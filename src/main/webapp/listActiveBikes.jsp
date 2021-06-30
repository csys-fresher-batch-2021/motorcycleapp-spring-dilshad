<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Bikes</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>List of Available bikes</h3>
		<table border="1">
			<thead>
				<tr>
					<th>Bike Number</th>
					<th>Manufacturer</th>
					<th>Model</th>
					<th>Color</th>
					<th>Odometer Reading</th>
					<th>Manufacture Year</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody id="bike-list"></tbody>
		</table>
		<script id="bike-list-template" type="text/html">
		<tr>
			<td>\${bikeNumber}</td>
			<td>\${bikeManufacturer}</td>
			<td>\${bikeModel}</td>
			<td>\${bikeColor}</td>
			<td>\${engineDetails.odometerReading} Km</td>
			<td>\${engineDetails.manufactureYear }</td>
			<td>Rs.\${bikePrice}</td>
		</tr>
	</script>
		<br />
		<br />
		<a href="index.jsp">Return to Home page</a>
		<script>
			function getActiveBikeList() {
				//event.preventDefault();

				let url = "motorcycleapp/v1/auth/bike/active/true";
				console.log(url);
				axios.get(url).then(res => {
					let bikes = res.data;
					console.log(bikes);
					$("#bike-list-template").template("bikeTemplate");
					$.tmpl("bikeTemplate", bikes).appendTo("#bike-list");

				}).catch(err => {
					console.log(err);
					let data = err.response.data;

					if (data.errorMessage != null) {
						console.log("Error Message from Controller:" + data.errorMessage);
						alert(data.errorMessage);
					}
				});
			}
			getActiveBikeList();
		</script>
	</main>
</body>

</html>