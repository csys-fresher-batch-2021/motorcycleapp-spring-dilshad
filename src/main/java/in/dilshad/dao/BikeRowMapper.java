package in.dilshad.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import in.dilshad.model.BikeDetails;
import in.dilshad.model.BikeStatus;
import in.dilshad.model.EngineDetails;

public class BikeRowMapper implements RowMapper<BikeDetails> {

	@Override
	public BikeDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

		BikeDetails bikeDetails = new BikeDetails();
		bikeDetails.setBikeNumber(rs.getString("bike_number"));
		bikeDetails.setBikeManufacturer(rs.getString("manufacturer"));
		bikeDetails.setBikeModel(rs.getString("model"));
		bikeDetails.setBikeColor(rs.getString("color"));
		bikeDetails.setBikePrice(rs.getFloat("price"));
		bikeDetails.setManufacturerId(rs.getInt("manufacturer_id"));

		EngineDetails enginedetails = new EngineDetails();
		enginedetails.setManufactureYear(rs.getInt("manufacture_year"));
		enginedetails.setOdometerReading(rs.getInt("odometer_reading"));
		enginedetails.setFuelId(rs.getInt("fuel_id"));
		BikeStatus bikeStatus = new BikeStatus();
		Date date = rs.getDate("added_date");
		LocalDate localDate = date.toLocalDate();
		bikeStatus.setAddedDate(localDate);

		bikeDetails.setEngineDetails(enginedetails);
		bikeDetails.setBikeStatus(bikeStatus);

		return bikeDetails;
	}
}