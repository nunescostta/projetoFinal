package br.com.projetofinal.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer idEndereco;

	@Column(name = "lograduro", length = 150, nullable = false)
	private String logradouro;

	@Column(name = "complemento", length = 150, nullable = false)
	private String complemento;

	@Column(name = "numero", length = 8, nullable = false)
	private String numero;

	@Column(name = "bairro", length = 70, nullable = false)
	private String bairro;

	@Column(name = "cidade", length = 100, nullable = false)
	private String cidade;

	@Column(name = "uf", length = 2, nullable = false)
	private String uf;

	@Column(name = "cep", length = 10, nullable = false)
	private String cep;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	

}
