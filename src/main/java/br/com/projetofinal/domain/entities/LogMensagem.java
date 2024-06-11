package br.com.projetofinal.domain.entities;

import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_log")
public class LogMensagem {

	@Id
	@Column(name = "id_log")
	private UUID id;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "dataHora")
	private LocalTime dataHora;
	
	@Column(name = "mensagem")
	private String mensagem;
	
	@Column(name = "erro")
	private String erro;
	
}
