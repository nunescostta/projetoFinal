package br.com.projetofinal.infrastructure.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.projetofinal.domain.exception.ClienteNaoEncontradoException;

@ControllerAdvice
public class ClienteNaoEncontradoExceptionHandler {
	
	@ExceptionHandler(ClienteNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	public List<String> errorHandler(ClienteNaoEncontradoException e) {

		List<String> erros = new ArrayList<String>();
		erros.add(e.getMessage());
		return erros;
	}
}
