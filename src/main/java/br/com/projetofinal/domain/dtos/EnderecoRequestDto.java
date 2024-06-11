package br.com.projetofinal.domain.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoRequestDto {

	private String logradouro;

	private String complemento;

	private String numero;

	private String bairro;

	private String cidade;

	@Size(max = 2, message = "UF deve conter no maximo 2 caracteres")
	private String uf;

	private String cep;

}
