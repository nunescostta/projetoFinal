package br.com.projetofinal.infrastructure.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandlers extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// criando uma lista para armazenar as mensagens de erro
		List<String> erros = new ArrayList<String>();
		// capturando mensagens de erro de validação geradas pelo Bean Validation
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			erros.add(error.getField() + ": " + error.getDefaultMessage());
		}
		// capturando mensagens de erro de validação geradas pelo Bean Validation
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			erros.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		// ordenar a lista em ordem alfabetica
		Collections.sort(erros);
		// retornando a lista de erros junto com o código BAD REQUEST (400)
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
}