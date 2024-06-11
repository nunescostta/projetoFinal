package br.com.projetofinal.domain.dtos;

import lombok.Data;

@Data
public class EmailDto {

	public String destinatario;
	public String assunto;
	public String mensagem;
	
}
