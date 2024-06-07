package br.com.projetofinal.domain.exception;

public class CpfJaCadastradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CpfJaCadastradoException () {
		super ("O Cpf informado já se encontra na base de dados. Tente outro diferente");
	}
}
