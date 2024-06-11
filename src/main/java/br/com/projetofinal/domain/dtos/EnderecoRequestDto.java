package br.com.projetofinal.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoRequestDto {

	@Size(min = 5, max = 20 , message = "Informe um Logradouro com no minimo 5 caracteres")
	@NotBlank(message = "Por favor, insira um Logradouro")
	private String logradouro;

	@Size(min = 5, max = 50, message = "Informe um Complemento com no minimo 5 caracteres")
	@NotBlank(message = "Por favor, insira um Complemento")
	private String complemento;

	@Size(min =1 , max = 6, message = "Informe um Numero com no minimo 1 caracteres")
	@NotBlank(message = "Por favor, insira um Numero")
	private String numero;
	
	@Size(min = 5, max = 20, message = "Informe um Bairro com no minimo 5 caracteres")
	@NotBlank(message = "Por favor, insira um Bairro")
	private String bairro;

	@Size(min = 5, max = 20, message = "Informe um Cidade com no minimo 5 caracteres")
	@NotBlank(message = "Por favor, insira um Cidade")
	private String cidade;

	@Size(max = 2, message = "UF deve conter no maximo 2 caracteres")
	@NotBlank(message = "Por favor, insira um UF")
	private String uf;

	@Size(min = 9, message = "Informe um Logradouro com no minimo 9 caracteres")
	@NotBlank(message = "Por favor, insira um Cep")
	private String cep;

}
