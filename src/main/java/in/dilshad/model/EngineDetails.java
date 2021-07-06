package in.dilshad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class EngineDetails {

	private Integer odometerReading;

	private Integer fuelId;

	private String fuelType;

	private Integer manufactureYear;

	public EngineDetails(Integer odometerReading, Integer fuelType, Integer manufactureYear) {
		super();
		this.odometerReading = odometerReading;
		this.fuelId = fuelType;
		this.manufactureYear = manufactureYear;
	}

}
