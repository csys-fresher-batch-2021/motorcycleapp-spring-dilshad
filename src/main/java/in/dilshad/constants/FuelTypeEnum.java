package in.dilshad.constants;

public enum FuelTypeEnum {
	PETROL("Petrol is costly", 89), ELECTRIC("Electric is economic", 32);

	private final String property;
	private final Integer weightage;

	private FuelTypeEnum(String name, Integer weightage) {
		this.property = name;
		this.weightage = weightage;
	}

	public Object getProperty() {
		return property;
	}

	public Integer getWeightage() {
		return weightage;
	}

}
