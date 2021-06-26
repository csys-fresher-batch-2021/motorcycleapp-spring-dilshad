package in.dilshad.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class BikeDetails {

	private String bikeNumber;

	private String bikeManufacturer;

	private String bikeModel;

	private String bikeColor;

	private float bikePrice;

	private EngineDetails engineDetails;

	private BikeStatus bikeStatus;

	public BikeDetails(String bikeNumber, String bikeManufacturer, String bikeModel, String bikeColor, float bikePrice,
			EngineDetails engineDetails) {
		super();
		this.bikeNumber = bikeNumber;
		this.bikeManufacturer = bikeManufacturer;
		this.bikeModel = bikeModel;
		this.bikeColor = bikeColor;
		this.bikePrice = bikePrice;
		this.engineDetails = engineDetails;
	}
}
