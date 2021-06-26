package in.dilshad.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.dilshad.dao.BikeRepository;
import in.dilshad.model.BikeDetails;
import in.dilshad.model.BikeStatus;
import in.dilshad.model.BookingStatusEnum;

@Service
public class BikeService {

	@Autowired
	BikeRepository bikeRepository;

	public boolean addBike(BikeDetails bikeDetails) {
		BikeStatus bikeStatus = new BikeStatus();
		bikeStatus.setAddedDate(LocalDate.now());
		bikeStatus.setAdminVerified(false);
		bikeStatus.setBookingStatus(BookingStatusEnum.NOT);
		bikeDetails.setBikeStatus(bikeStatus);

		return bikeRepository.save(bikeDetails);
	}
	
	public Integer countBikes() {
		return bikeRepository.count();
	}
}
