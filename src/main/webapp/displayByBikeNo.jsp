<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html>
	<html lang="en">

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta charset="ISO-8859-1">
		<title>Bike Details</title>
	</head>

	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<main class="container-fluid">

			<br/>
			<h3>Find by plate number</h3>
			<br/>
			<form onsubmit="getBike()">
				<label for="plateNo">Enter plateNo :</label>
				<input type="text" required="required" id="plateNo" name="plateNo">
				<button>Search</button>
			</form>

			<br/>
			<h6>Bike Details</h6>
			<br/>
			<p id="bikeNumber"></p>
			<p id="bikeManufacturer"></p>
			<p id="bikeModel"></p>
			<p id="bikeColor"></p>
			<p id="bikePrice"></p>
			<p id="odometerReading"></p>
			<p id="fuelType"></p>
			<p id="manufactureYear"></p>

			<br/>
			<script>

				function getBike() {
					event.preventDefault();

					let plateNo = document.querySelector("#plateNo").value;
					console.log(plateNo);
					let url = "motorcycleapp/v1/auth/bike/" + plateNo;
					console.log(url);
					axios.get(url).then(res => {
						let bike = res.data;
						console.log(bike);
						// document.querySelector("#bikeNumber").innerHTML = "bike Number: " + bike.bikeNumber;
						$("#bikeNumber").html("Bike Number: " + bike.bikeNumber);
						$("#bikeManufacturer").html("Bike Manufacturer: " + bike.bikeManufacturer);
						$("#bikeModel").html("Bike Model: " + bike.bikeModel);
						$("#bikeColor").html("Color: " + bike.bikeColor);
						$("#bikePrice").html("Price: Rs." + bike.bikePrice);
						$("#odometerReading").html("Odometer Reading: " + bike.engineDetails.odometerReading + "Km");
						$("#fuelType").html("Fuel Type: " + bike.engineDetails.fuelType);
						$("#manufactureYear").html("Manufacture Year: " + bike.engineDetails.manufactureYear);

					}).catch(err => {
						let data = err.response.data;

						if (data.errorMessage != null) {
							console.log("Error Message from Controller:" + data.errorMessage);
							alert(data.errorMessage);
							location.reload();
						}
					})
				}

			</script>
		</main>
	</body>

	</html>