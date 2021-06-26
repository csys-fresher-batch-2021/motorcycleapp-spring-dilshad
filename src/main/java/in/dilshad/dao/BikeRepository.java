package in.dilshad.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.dilshad.model.BikeDetails;

@Repository
public class BikeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean save(BikeDetails bikeDetails) {
		String sql = "INSERT INTO motorcycle_details (bike_number, manufacturer, model, color, price, odometer_reading, fuel_type, manufacture_year, added_date, verification_status, market_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = { bikeDetails.getBikeNumber(), 
				bikeDetails.getBikeManufacturer(), 
				bikeDetails.getBikeModel(),
				bikeDetails.getBikeColor(), 
				bikeDetails.getBikePrice(),
				bikeDetails.getEngineDetails().getOdometerReading(),
				bikeDetails.getEngineDetails().getFuelType().toString(),
				bikeDetails.getEngineDetails().getManufactureYear(), 
				bikeDetails.getBikeStatus().getAddedDate(),
				bikeDetails.getBikeStatus().isAdminVerified(),
				bikeDetails.getBikeStatus().getBookingStatus().toString() };

		int rows = jdbcTemplate.update(sql, params);
		return rows == 1;
	}
}
