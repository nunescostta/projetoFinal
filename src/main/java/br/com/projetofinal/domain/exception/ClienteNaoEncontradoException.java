package br.com.projetofinal.domain.exception;

public class ClienteNaoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException () {
		super ("O ID informado não se encontra no banco de dados do sistema");
	}
}
