package br.com.projetofinal.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projetofinal.domain.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

	@Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf")
	Cliente findByCpf(@Param("cpf") String cpf);
	
	@Query("select c from Cliente c left join fetch c.enderecos where c.id = :id")
	Cliente getClienteAndEnderecoId (@Param("id") UUID id);
	
	@Query("select distinct c from Cliente c left join fetch c.enderecos order by c.nome")
	List<Cliente> getClienteOrderNome();
	
	

}
