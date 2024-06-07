package br.com.projetofinal.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.domain.dtos.ClienteRequestDto;
import br.com.projetofinal.domain.dtos.ClienteResponseDto;
import br.com.projetofinal.domain.interfaces.ClienteDomainService;
import jakarta.validation.Valid;

@RequestMapping("api/clientes")
@RestController
public class ClienteController {

	@Autowired
	private ClienteDomainService clienteDomainService;

	@PostMapping("criar")
	public ResponseEntity<ClienteResponseDto> post(@RequestBody @Valid ClienteRequestDto dto) {

		ClienteResponseDto response = clienteDomainService.post(dto);

		return ResponseEntity.status(201).body(response);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponseDto> put(@PathVariable UUID id, @RequestBody @Valid ClienteRequestDto dto) {

		ClienteResponseDto response = clienteDomainService.put(id, dto);

		return ResponseEntity.status(200).body(response);
	}

	@GetMapping
	public ResponseEntity<List<ClienteResponseDto>> getAll() {

		List<ClienteResponseDto> response = clienteDomainService.getClientesOrderName();

		return ResponseEntity.status(200).body(response);
	}

	@GetMapping("buscar/{id}")
	public ResponseEntity<ClienteResponseDto> getClientesAndEnderecoId(@PathVariable UUID id) {

		ClienteResponseDto response = clienteDomainService.getId(id);

		return ResponseEntity.status(200).body(response);

	}

	@DeleteMapping("excluir{id}")
	public ResponseEntity<ClienteResponseDto> excluir(@PathVariable UUID id) {

		ClienteResponseDto response = clienteDomainService.delete(id);
		
		return ResponseEntity.status(200).body(response);
	}
}
