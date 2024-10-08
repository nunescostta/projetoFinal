package br.com.projetofinal.domain.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteRequestDto {

	@Size(min = 8, max = 100, message = "Informe um nome com no minimo 8 caracteres")
	@NotBlank(message = "Por favor, informe o Nome do Cliente")
	private String nome;

	@Email(message = "Por favor, informe um Email valido")
	@NotBlank(message = "Por favor, informe o Email do Cliente")
	private String email;

	@Size(min = 8, max = 100, message = "Por favor, informe um Cpf valido")
	@Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$", message = "Insira o CPF contendo Pontos e Traço")
	@NotBlank(message = "Por favor, informe um Cpf")
	private String cpf;
	
	@NotNull(message = "Por favor, informe a Data de Nascimento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataNascimento;

	private EnderecoRequestDto endereco;
}
