package in.dilshad.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.dilshad.constants.BookingStatusEnum;
import in.dilshad.dao.DAOFactory;
import in.dilshad.dao.IBikeRepository;
import in.dilshad.exceptions.DBException;
import in.dilshad.model.BikeCount;
import in.dilshad.model.BikeDetails;
import in.dilshad.model.BikeStatus;

/**
 * Bike service class accepts data form Controller. Business validation is done
 * and adds few default fields. Then it returns to DAO layer.
 *
 * @author dils2654
 *
 */
@Service
public class BikeService {

	@Autowired
	IBikeRepository bikeRepository;

	/**
	 * It accepts basic bike details, mail ID of owner and adds few default values
	 * and passes to DAO layer.
	 *
	 * @param bikeDetails
	 * @return
	 * @throws SQLException
	 */
	public boolean addBike(BikeDetails bikeDetails, String emailId) throws SQLException {
		BikeStatus bikeStatus = new BikeStatus();
		bikeStatus.setAddedDate(LocalDate.now());
		bikeStatus.setAdminVerified(false);
		bikeStatus.setBookingStatus(BookingStatusEnum.NOTVERIFIED);
		bikeDetails.setBikeStatus(bikeStatus);
		return bikeRepository.save(bikeDetails, emailId);
	}

	/**
	 * Returns the count of bikes available in table of various categories.
	 *
	 * @return
	 */
	public BikeCount countBikes() {
		return bikeRepository.count();
	}

	/**
	 * Get the asset value from database.
	 *
	 * @return
	 */
	public float getBikeAssets() {
		return bikeRepository.getAsset();

	}

	/**
	 * Passes the status as argument and gets the list of bikes
	 *
	 * @param status
	 * @return
	 */
	public List<BikeDetails> getAllBikes(boolean status) {
		return bikeRepository.findAllByStatus(status);
	}

	/**
	 * Passes bike number to DAO layer and returns bike specification to the service
	 * layer.
	 *
	 * @param bikeNumber
	 * @return
	 * @throws DBException
	 */
	public BikeDetails getByBikeNumber(String bikeNumber) throws DBException {

		return bikeRepository.findByBikeNumber(bikeNumber);
	}

	/**
	 * Passes bike number to DAO layer and returns true when success and false when
	 * invalid bike number is given.
	 *
	 * @param bikeNumber
	 * @return
	 */
	public boolean removeBike(String bikeNumber) {
		return bikeRepository.remove(bikeNumber);
	}

	/**
	 * Give the bike number & price to the DAO layer. Throws exception when bike
	 * number is not found in database.
	 *
	 * @param bikeNumber
	 * @param revisedPrice
	 * @throws Exception
	 */
	public void updatePrice(String bikeNumber, float revisedPrice) throws Exception {
		boolean isUpdated = bikeRepository.updatePrice(bikeNumber, revisedPrice);
		if (!isUpdated)
			throw new Exception();
	}

	/**
	 * Updates the status of bike from false to true.
	 *
	 * @param bikeNumber
	 * @return
	 */
	public boolean updateBikeVerificationStatustoTrue(String bikeNumber) {
		return bikeRepository.updateBikeStatus(bikeNumber);
	}
}
