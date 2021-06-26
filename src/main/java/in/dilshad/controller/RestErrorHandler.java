package in.dilshad.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import in.dilshad.dto.Message;
import in.dilshad.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * To handle Hibernate Validation Exception.
 *
 * @author dils2654
 *
 */
@Slf4j
@ControllerAdvice
public class RestErrorHandler {

	/**
	 * To handle ValidationException
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Message processValidationError(ValidationException ex) {
		Message message = new Message(ex.getMessage());
		log.debug("########### Validation Exception - " + ex.getMessage());
		return message;
	}

	/**
	 * To handle ConstraintViolationException.
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<Message> handleConstraintViolationException(ConstraintViolationException e) {
		Message message = new Message("ConstraintViolationException: " + e.getMessage());
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	/**
	 * To Handle MethodArgumentNotValidException
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<Message> handleConstraintViolationException2(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getFieldErrors();
		Map<String, String> messages = new LinkedHashMap<>();
		for (FieldError field : fieldErrors) {
			messages.put(field.getField(), field.getDefaultMessage());
		}
		Message message = new Message();
		message.setErrors(messages);
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
}
