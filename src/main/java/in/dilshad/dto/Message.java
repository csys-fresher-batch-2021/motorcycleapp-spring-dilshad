package in.dilshad.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * To transfer List of messages from back end to front end.
 *
 * @author dils2654
 *
 */
@JsonInclude(value = Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

	public Message(String message) {
		this.errorMessage = message;
	}

	private String infoMessage;

	private String errorMessage;

	private Map<String, String> errors;

	private String controllerMessage;
}


