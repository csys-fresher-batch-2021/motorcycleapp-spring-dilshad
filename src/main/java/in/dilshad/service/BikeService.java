package in.dilshad.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.dilshad.constants.BookingStatusEnum;
import in.dilshad.dao.BikeRepository;
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
	BikeRepository bikeRepository;

	/**
	 * It accepts basic bike details and adds few default values and passes to DAO
	 * layer.
	 *
	 * @param bikeDetails
	 * @return
	 */
	public boolean addBike(BikeDetails bikeDetails) {
		BikeStatus bikeStatus = new BikeStatus();
		bikeStatus.setAddedDate(LocalDate.now());
		bikeStatus.setAdminVerified(false);
		bikeStatus.setBookingStatus(BookingStatusEnum.NOT);
		bikeDetails.setBikeStatus(bikeStatus);
		return bikeRepository.save(bikeDetails);
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
	 */
	public BikeDetails getByBikeNumber(String bikeNumber) {
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
}
