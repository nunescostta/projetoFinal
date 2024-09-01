package br.com.projetofinal.domain.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ClienteResponseDto {

	private UUID id;
	
	private String nome;

	private String email;

	private String cpf;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataNascimento;

    private List<EnderecoResponseDto> enderecos;

}
