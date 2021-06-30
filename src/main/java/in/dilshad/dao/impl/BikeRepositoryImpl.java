package in.dilshad.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.dilshad.dao.BikeRowMapper;
import in.dilshad.dao.IBikeRepository;
import in.dilshad.model.BikeCount;
import in.dilshad.model.BikeDetails;

/**
 * To store and retrieve bikes from the database
 *
 * @author dils2654
 *
 */
@Repository
public class BikeRepositoryImpl implements IBikeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Inserts the bike specification into the database.
	 *
	 * @param bikeDetails
	 * @return
	 */
	@Override
	public boolean save(BikeDetails bikeDetails) {
		String sql = "INSERT INTO motorcycle_details (bike_number, manufacturer_id, model, color, price, odometer_reading, fuel_type, manufacture_year, added_date, verification_status, market_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = { bikeDetails.getBikeNumber(), bikeDetails.getManufacturerId(), bikeDetails.getBikeModel(),
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
	@Override
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

	/**
	 * Get the sum of total amount of verified active bikes.
	 *
	 * @return
	 */
	@Override
	public float getAsset() throws DbActionExecutionException {
		String sql = "SELECT SUM(price) FROM motorcycle_details WHERE market_status != 'BOOKED' AND verification_status = 'true'";
		return jdbcTemplate.queryForObject(sql, Float.class);
	}

	/**
	 * Fetches the list of bike details without including the market_status =
	 * "BOOKED". if(status = true) - returns the list of verified bikes, else
	 * returns the list of unverified bikes.
	 *
	 * @param status
	 * @return
	 */
	@Override
	public List<BikeDetails> findAllByStatus(boolean status) {

		String sql = "SELECT md.bike_number, md.manufacturer_id, bm.manufacturer, model, color, price, odometer_reading, manufacture_year, md.added_date, market_status FROM motorcycle_details md , bike_manufacturer bm WHERE md.manufacturer_id= bm.id and verification_status = ? AND market_status != 'BOOKED'";
		List<BikeDetails> bikeList = jdbcTemplate.query(sql, new BikeRowMapper(), status);
		return bikeList;
	}

	/**
	 * Fetches the bike specification from the database whose status is active for
	 * the valid bike number.
	 *
	 * @param bikeNumber
	 * @return
	 */
	@Override
	public BikeDetails findByBikeNumber(String bikeNumber) {
		String sql = "SELECT bike_number, manufacturer, model, color, price, odometer_reading, manufacture_year, added_date, market_status FROM motorcycle_details WHERE bike_number = ? AND market_status != 'BOOKED' AND verification_status = true";
		BikeDetails bikeDetails = null;
		bikeDetails = jdbcTemplate.queryForObject(sql, new BikeRowMapper(), bikeNumber);
		return bikeDetails;
	}

	/**
	 * Gets bike number and removes the corresponding record for the when valid bike
	 * number is provided. Returns true when valid record is removed. Returns false
	 * when record is not removed.
	 *
	 * @param bikeNumber
	 * @return
	 */
	@Override
	public boolean remove(String bikeNumber) {
		String sql = "DELETE FROM motorcycle_details WHERE bike_number = ?";
		int rows = jdbcTemplate.update(sql, bikeNumber);
		return rows == 1 ? true : false;
	}

	/**
	 * Returns true when valid bikes number is passed and updates the price for the
	 * given bike number. Return false when bike number not present in table.
	 *
	 * @param bikeNumber
	 * @param revisedPrice
	 * @return
	 */
	@Override
	public boolean updatePrice(String bikeNumber, float revisedPrice) {
		String sql = "UPDATE motorcycle_details SET price = ? WHERE bike_number = ?";
		int rows = jdbcTemplate.update(sql, revisedPrice, bikeNumber);
		return rows == 1 ? true : false;
	}
}
