package in.dilshad.model;

import in.dilshad.constants.FuelTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class EngineDetails {

	private Integer odometerReading;

	private FuelTypeEnum fuelType;

	private Integer manufactureYear;

	public EngineDetails(Integer odometerReading, FuelTypeEnum fuelType, Integer manufactureYear) {
		super();
		this.odometerReading = odometerReading;
		this.fuelType = fuelType;
		this.manufactureYear = manufactureYear;
	}

}
