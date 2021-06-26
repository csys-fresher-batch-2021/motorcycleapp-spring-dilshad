package in.dilshad.model;


import java.time.LocalDate;

import in.dilshad.constants.BookingStatusEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BikeStatus {

	private LocalDate addedDate;

	private boolean adminVerified;

	private BookingStatusEnum bookingStatus;

}

