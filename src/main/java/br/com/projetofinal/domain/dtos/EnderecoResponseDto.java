package br.com.projetofinal.domain.dtos;

import lombok.Data;

@Data
public class EnderecoResponseDto {

	private Integer id;
	
	private String logradouro;

	private String complemento;

	private String numero;

	private String bairro;

	private String cidade;

	private String uf;

	private String cep;

}
