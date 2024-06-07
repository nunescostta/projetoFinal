package br.com.projetofinal.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.projetofinal.domain.dtos.ClienteRequestDto;
import br.com.projetofinal.domain.dtos.ClienteResponseDto;

public interface ClienteDomainService {

	ClienteResponseDto post(ClienteRequestDto dto);

	ClienteResponseDto put(UUID id, ClienteRequestDto dto);

	List<ClienteResponseDto> getClientesOrderName();

	ClienteResponseDto getId(UUID id);

	ClienteResponseDto delete(UUID id);
}
