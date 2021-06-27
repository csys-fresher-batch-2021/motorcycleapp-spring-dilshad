package in.dilshad.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;

import in.dilshad.model.BikeCount;
import in.dilshad.model.BikeDetails;

public interface IBikeRepository {

	/**
	 * Inserts the bike specification into the database.
	 *
	 * @param bikeDetails
	 * @return
	 */
	boolean save(BikeDetails bikeDetails);

	/**
	 * Return the count of bikes.
	 *
	 * Verified bikes, Yet to verify bike, Verified Gasoline bike, Verified Electric
	 * bike, Sold bike.
	 *
	 * @return
	 */
	BikeCount count() throws DataAccessException;

	/**
	 * Get the sum of total amount of verified active bikes.
	 *
	 * @return
	 */
	float getAsset() throws DbActionExecutionException;

	/**
	 * Fetches the list of bike details without including the market_status =
	 * "BOOKED". if(status = true) - returns the list of verified bikes, else
	 * returns the list of unverified bikes.
	 *
	 * @param status
	 * @return
	 */
	List<BikeDetails> findAllByStatus(boolean status);

	/**
	 * Fetches the bike specification from the database whose status is active for
	 * the valid bike number.
	 *
	 * @param bikeNumber
	 * @return
	 */
	BikeDetails findByBikeNumber(String bikeNumber);

	/**
	 * Gets bike number and removes the corresponding record for the when valid bike
	 * number is provided. Returns true when valid record is removed. Returns false
	 * when record is not removed.
	 *
	 * @param bikeNumber
	 * @return
	 */
	boolean remove(String bikeNumber);

	/**
	 * Returns true when valid bikes number is passed and updates the price for the
	 * given bike number. Return false when bike number not present in table.
	 *
	 * @param bikeNumber
	 * @param revisedPrice
	 * @return
	 */
	boolean updatePrice(String bikeNumber, float revisedPrice);

}