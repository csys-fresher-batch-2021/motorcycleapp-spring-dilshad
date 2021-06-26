package in.dilshad.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.dilshad.dao.BikeRepository;
import in.dilshad.model.BikeDetails;
import in.dilshad.model.BikeStatus;
import in.dilshad.model.BookingStatusEnum;

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
	 * Returns the total number of bikes available in table.
	 *
	 * @return
	 */
	public Integer countBikes() {
		return bikeRepository.count();
	}
}
