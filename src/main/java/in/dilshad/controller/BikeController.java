package in.dilshad.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dilshad.dto.BikeDetailsDTO;
import in.dilshad.dto.Message;
import in.dilshad.model.BikeCount;
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
		BikeDetails bikeDetails = new BikeDetails(bikeDetailsDTO.getBikeNumber(), bikeDetailsDTO.getBikeManufacturer(),
				bikeDetailsDTO.getBikeModel(), bikeDetailsDTO.getBikeColor(), bikeDetailsDTO.getBikePrice(),
				engineDetails);

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
			BikeCount countOfBikes = bikeService.countBikes();
			return new ResponseEntity<>(countOfBikes, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message();
			message.setControllerMessage("Internal error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Returns the asset value of current active bikes.
	 *
	 * url: http://localhost:9000/motorcycleapp/v1/auth/bike/asset
	 *
	 * @return
	 */
	@GetMapping("asset")
	public ResponseEntity<?> getAsset() {
		try {
			Float netAmount = bikeService.getBikeAssets();
			return new ResponseEntity<>(netAmount, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message();
			message.setControllerMessage("Internal error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gives the list of bikes based on administrator verification status. If
	 * (active/true) - verified bike list. else(active/false) - list of unverified
	 * bike list.
	 *
	 * url: http://localhost:9000/motorcycleapp/v1/auth/bike/active/true
	 *
	 * @param status
	 * @return
	 */
	@GetMapping("active/{i}")
	public ResponseEntity<?> getBikeList(@PathVariable("i") Boolean status) {
		try {
			List<BikeDetails> bikeList = bikeService.getAllBikes(status);
			return new ResponseEntity<>(bikeList, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message();
			message.setControllerMessage("Unable to fetch Bike Details");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gives the detailed bike specification for the valid Bike number.
	 *
	 * url: http://localhost:9000/motorcycleapp/v1/auth/bike/TN-23-AR-3480
	 *
	 * @param bikeNumber
	 * @return
	 */
	@GetMapping("{id}")
	public ResponseEntity<?> findByBikeNumber(@PathVariable("id") String bikeNumber) {
		try {
			BikeDetails bikeDetails = bikeService.getByBikeNumber(bikeNumber);
			System.out.println(bikeDetails);

			return new ResponseEntity<>(bikeDetails, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message();
			message.setErrorMessage("Unable to fetch Bike Details");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Remove bike from when valid plate number is given.
	 *
	 * url: http://localhost:9000/motorcycleapp/v1/auth/bike/TN-23-AR-3480
	 *
	 * @param bikeNumber
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> remove(@PathVariable("id") String bikeNumber) {
		try {
			boolean isRemoved = bikeService.removeBike(bikeNumber);
			if (isRemoved) {
				Message message = new Message();
				message.setInfoMessage("Successfully removed the bike: " + bikeNumber);
				return new ResponseEntity<>(message, HttpStatus.OK);
			} else {
				Message message = new Message();
				message.setControllerMessage("Bike number: " + bikeNumber + " not found");
				return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			Message message = new Message();
			message.setControllerMessage("Unable to process your request");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Accepts bike number and revised price and passes it to service layer.
	 *
	 * url:
	 * http://localhost:9000/motorcycleapp/v1/auth/bike/TN-89-ER-3480/update-price
	 *
	 * @param bikeNumber
	 * @param bikeDetailsDTO
	 * @return
	 */
	@PatchMapping("{bikeNumber}/update-price")
	public ResponseEntity<?> updatePrice(@PathVariable("bikeNumber") String bikeNumber,
			@RequestBody BikeDetailsDTO bikeDetailsDTO) {
		try {
			bikeService.updatePrice(bikeNumber, bikeDetailsDTO.getBikePrice());
			Message message = new Message();
			message.setInfoMessage("Successfully updated the price");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message();
			message.setControllerMessage(" Bike number not found");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

	}
}
