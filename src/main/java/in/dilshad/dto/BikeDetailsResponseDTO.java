package in.dilshad.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import in.dilshad.constants.BookingStatusEnum;
import in.dilshad.constants.FuelTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * To transfer Bike Specification for front end to back end. Hibernate
 * Validation is performed.
 *
 * @author dils2654
 *
 */
@Data
@NoArgsConstructor
public class BikeDetailsResponseDTO {

	private String bikeNumber;

	private Integer manufacturerId;

	private String bikeManufacturer;

	private String bikeModel;

	private String bikeColor;

	private float bikePrice;

	private Integer odometerReading;

	private Integer fuelId;

	private FuelTypeEnum fuelType;

	private Integer manufactureYear;

	private LocalDate addedDate;

	private BookingStatusEnum bookingStatus;

}
