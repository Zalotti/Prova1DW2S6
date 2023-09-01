package br.edu.ifsp.arq.dw2s6.exam.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.edu.ifsp.arq.dw2s6.exam.service.exception.NonExistentOrInactiveUserException;


@ControllerAdvice
public class ExamExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String customerMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause().toString();
		
		return handleExceptionInternal(ex, new Error(customerMessage, developerMessage), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<Error> errors = createErrorList(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Error> createErrorList(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String customerMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String developerMessage = fieldError.toString();
			errors.add(new Error(customerMessage, developerMessage));
		}
		return errors;
	}

	public static class Error{
		private String customerMessage;
		private String developerMessage;
		public Error(String userMessage, String developerMessage) {
			this.customerMessage = userMessage;
			this.developerMessage = developerMessage;
		}
		
		public String getUserMessage() {
			return customerMessage;
		}
		
		public String getDeveloperMessage() {
			return developerMessage;
		}
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String userMessage = messageSource.getMessage("resource.not-found", null, 
			LocaleContextHolder.getLocale());
		String developerMessage = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

}
