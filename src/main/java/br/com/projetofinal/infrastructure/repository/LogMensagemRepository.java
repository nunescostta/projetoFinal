package br.com.projetofinal.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetofinal.domain.entities.LogMensagem;

@Repository
public interface LogMensagemRepository extends JpaRepository <LogMensagem, UUID> {

}
