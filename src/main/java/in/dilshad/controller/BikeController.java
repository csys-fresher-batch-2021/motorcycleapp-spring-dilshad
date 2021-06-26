package in.dilshad.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dilshad.dto.BikeDetailsDTO;
import in.dilshad.dto.Message;
import in.dilshad.model.BikeDetails;
import in.dilshad.model.EngineDetails;
import in.dilshad.service.BikeService;

/**
 * Bike Controller class is serves various features for manipulation of bikes.
 *
 * @author dils2654
 *
 */
@RestController
@RequestMapping("motorcycleapp/v1/auth/bike")
public class BikeController {

	@Autowired
	BikeService bikeService;

	/**
	 * Accepts bike specifications and passes it to service layer
	 *
	 * url: http://localhost:9000/motorcycleapp/v1/auth/bike/add
	 *
	 * @param bikeDetailsDTO
	 * @return
	 */
	@PostMapping("add")
	public ResponseEntity<?> addBike(@Valid @RequestBody BikeDetailsDTO bikeDetailsDTO) {

		EngineDetails engineDetails = new EngineDetails(bikeDetailsDTO.getOdometerReading(),
				bikeDetailsDTO.getFuelType(), bikeDetailsDTO.getManufactureYear());
		BikeDetails bikeDetails = new BikeDetails(bikeDetailsDTO.getBikeNumber(), bikeDetailsDTO.getBikeManufacturer(), bikeDetailsDTO.getBikeModel(), bikeDetailsDTO.getBikeColor(), bikeDetailsDTO.getBikePrice(), engineDetails);

		try {
			bikeService.addBike(bikeDetails);
			Message message = new Message();
			message.setInfoMessage("Successfully added");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message();
			message.setControllerMessage("Unable to add Bike Details");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

		}
	}

	/**
	 * Gives the total number of bikes available (irrespective of status) in the
	 * database table
	 *
	 * url: http://localhost:9000/motorcycleapp/v1/auth/bike/count
	 *
	 * @return
	 */
	@GetMapping("count")
	public ResponseEntity<?> count() {
		try {
			Integer countOfBikes = bikeService.countBikes();
			return new ResponseEntity<>(countOfBikes, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message();
			message.setControllerMessage("Internal error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

