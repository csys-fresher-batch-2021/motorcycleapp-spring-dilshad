package in.dilshad.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class BikeDetailsDTO {

	@NotNull
	@NotEmpty(message = "Bike Plate number cannot be empty")
	@Size(min = 11, max = 14, message = "Bike number should be in correct format")
	private String bikeNumber;

	@NotNull
	@NotEmpty(message = "Bike manufacturer name cannot be empty")
	@Size(min = 2, max = 30, message = "Name must be between 3 and 20 characters")
	private String bikeManufacturer;

	@NotNull
	@NotEmpty(message = "Bike model name cannot be empty")
	@Size(min = 2, max = 30, message = "Name must be between 2 and 20 characters")
	private String bikeModel;

	@NotNull
	@NotEmpty(message = "Member name cannot be empty")
	@Size(min = 3, max = 20, message = "Name must be between 3 and 30 characters")
	private String bikeColor;

	@NotNull
	private float bikePrice;

	@NotNull
	private Integer odometerReading;

	private FuelTypeEnum fuelType;

	@NotNull
	private Integer manufactureYear;
}
