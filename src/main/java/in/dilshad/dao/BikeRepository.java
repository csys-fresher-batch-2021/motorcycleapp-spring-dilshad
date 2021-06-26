package in.dilshad.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.dilshad.model.BikeCount;
import in.dilshad.model.BikeDetails;

/**
 * To store and retrieve bikes from the database
 *
 * @author dils2654
 *
 */
@Repository
public class BikeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	/**
	 * Inserts the bike specification into the database.
	 *
	 * @param bikeDetails
	 * @return
	 */
	public boolean save(BikeDetails bikeDetails) {
		String sql = "INSERT INTO motorcycle_details (bike_number, manufacturer, model, color, price, odometer_reading, fuel_type, manufacture_year, added_date, verification_status, market_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = { bikeDetails.getBikeNumber(), bikeDetails.getBikeManufacturer(), bikeDetails.getBikeModel(),
				bikeDetails.getBikeColor(), bikeDetails.getBikePrice(),
				bikeDetails.getEngineDetails().getOdometerReading(),
				bikeDetails.getEngineDetails().getFuelType().toString(),
				bikeDetails.getEngineDetails().getManufactureYear(), bikeDetails.getBikeStatus().getAddedDate(),
				bikeDetails.getBikeStatus().isAdminVerified(),
				bikeDetails.getBikeStatus().getBookingStatus().toString() };

		int rows = jdbcTemplate.update(sql, params);
		return rows == 1;
	}

	/**
	 * Return the count of bikes.
	 *
	 * Verified bikes, Yet to verify bike, Verified Gasoline bike, Verified Electric
	 * bike, Sold bike.
	 *
	 * @return
	 */
	public BikeCount count() throws DataAccessException {
		BikeCount bikeCount = new BikeCount();

		String sql1 = "SELECT COUNT(bike_number) FROM motorcycle_details WHERE verification_status = true AND market_status != 'BOOKED'";
		bikeCount.setVerifiedBike(jdbcTemplate.queryForObject(sql1, Integer.class));

		String sql2 = "SELECT COUNT(bike_number) FROM motorcycle_details WHERE verification_status = false";
		bikeCount.setYetToVerifyBike(jdbcTemplate.queryForObject(sql2, Integer.class));

		String sql3 = "SELECT COUNT(bike_number) FROM motorcycle_details WHERE verification_status = true AND market_status != 'BOOKED' AND fuel_type = 'PETROL'";
		bikeCount.setPetrolBike(jdbcTemplate.queryForObject(sql3, Integer.class));

		String sql4 = "SELECT COUNT(bike_number) FROM motorcycle_details WHERE verification_status = true AND market_status != 'BOOKED' AND fuel_type = 'ELECTRIC'";
		bikeCount.setElectricBike(jdbcTemplate.queryForObject(sql4, Integer.class));

		String sql5 = "SELECT COUNT(bike_number) FROM motorcycle_details WHERE market_status = 'BOOKED'";
		bikeCount.setSoldBike(jdbcTemplate.queryForObject(sql5, Integer.class));

		return bikeCount;
	}
}
