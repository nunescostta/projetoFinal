package br.com.projetofinal.domain.entities;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_cliente")
public class Cliente {

	@Id
	@Column(name = "idCliente")
	private UUID idCliente;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "email", length = 30, nullable = false)
	private String email;

	@Column(name = "cpf", length = 15, nullable = false)
	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataNascimento", nullable = false)
	private Date dataNascimento;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Endereco> enderecos;

}
