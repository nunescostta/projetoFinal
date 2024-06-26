package br.com.projetofinal.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.domain.dtos.ClienteRequestDto;
import br.com.projetofinal.domain.dtos.ClienteResponseDto;
import br.com.projetofinal.domain.dtos.EmailDto;
import br.com.projetofinal.domain.entities.Cliente;
import br.com.projetofinal.domain.entities.Endereco;
import br.com.projetofinal.domain.exception.ClienteNaoEncontradoException;
import br.com.projetofinal.domain.exception.CpfJaCadastradoException;
import br.com.projetofinal.domain.interfaces.ClienteDomainService;
import br.com.projetofinal.infrastructure.components.EmailProducerComponent;
import br.com.projetofinal.infrastructure.repository.ClienteRepository;

@Service
public class ClienteDomainServiceImpl implements ClienteDomainService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EmailProducerComponent emailProducerComponent;

	@Override
	public ClienteResponseDto post(ClienteRequestDto dto) {

		if (clienteRepository.findByCpf(dto.getCpf()) != null) {
			throw new CpfJaCadastradoException();
		}

		Cliente cliente = modelMapper.map(dto, Cliente.class);
		cliente.setIdCliente(UUID.randomUUID());
		cliente.setEnderecos(new ArrayList<>());

		Endereco endereco = modelMapper.map(dto.getEndereco(), Endereco.class);
		endereco.setCliente(cliente);

		cliente.getEnderecos().add(endereco);

		clienteRepository.save(cliente);

		enviarEmailDeBoasVindas(cliente);

		return modelMapper.map(cliente, ClienteResponseDto.class);

	}

	private void enviarEmailDeBoasVindas(Cliente cliente) {
		String to = cliente.getEmail();
		String subject = "Seja bem vindo ao sistema de Endereços - Projeto Final.";
		String body = "Olá, " + cliente.getNome() + "\nSua conta foi criada com sucesso" + "\nSeja bem vindo!"
				+ "\n\nAtt, " + "\nJean Nunes";

		EmailDto dto = new EmailDto();
		dto.setDestinatario(to);
		dto.setAssunto(subject);
		dto.setMensagem(body);

		try {
			emailProducerComponent.sendMessage(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public ClienteResponseDto put(UUID id, ClienteRequestDto dto) {

		Optional<Cliente> opcaoCliente = clienteRepository.findById(id);

		if (opcaoCliente.isEmpty()) {
			throw new ClienteNaoEncontradoException();
		}

		Cliente cliente = clienteRepository.findById(id).get();
		modelMapper.map(dto, cliente);

		if (cliente.getEnderecos() != null) {
			cliente.getEnderecos().clear();
		} else {
			cliente.setEnderecos(new ArrayList<>());
		}

		Endereco endereco = modelMapper.map(dto.getEndereco(), Endereco.class);
		endereco.setCliente(cliente);

		cliente.getEnderecos().add(endereco);

		clienteRepository.save(cliente);

		return modelMapper.map(cliente, ClienteResponseDto.class);
	}

	@Override
	public List<ClienteResponseDto> getClientesOrderName() {

		List<Cliente> clientes = clienteRepository.getClienteOrderNome();

		return clientes.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ClienteResponseDto getId(UUID id) {

		Optional<Cliente> opcaoCliente = clienteRepository.findById(id);

		if (opcaoCliente.isEmpty()) {
			throw new ClienteNaoEncontradoException();
		}

		Cliente cliente = clienteRepository.getClienteAndEnderecoId(id);

		return modelMapper.map(cliente, ClienteResponseDto.class);
	}

	@Override
	public ClienteResponseDto delete(UUID id) {

		Optional<Cliente> opcaoCliente = clienteRepository.findById(id);

		if (opcaoCliente.isEmpty()) {
			throw new ClienteNaoEncontradoException();
		}

		Cliente cliente = clienteRepository.findById(id).get();

		clienteRepository.delete(cliente);

		return modelMapper.map(cliente, ClienteResponseDto.class);
	}

}
