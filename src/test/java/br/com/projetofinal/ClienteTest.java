package br.com.projetofinal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;

import br.com.projetofinal.domain.dtos.ClienteRequestDto;
import br.com.projetofinal.domain.dtos.EnderecoRequestDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private static String nome;
	private static String email;
	private static String cpf;
	private static String dataNascimento;

	private static String logradouro;
	private static String complemento;
	private static String numero;
	private static String bairro;
	private static String cidade;
	private static String uf;
	private static String cep;

	@Test
	@Order(1)
	public void quandoCadastrarCliente_EntaoRetornar201() throws Exception {

		Faker faker = new Faker(new Locale("pt-Br"));

		ClienteRequestDto dto = new ClienteRequestDto();
		dto.setNome(faker.name().fullName());
		dto.setEmail(faker.internet().emailAddress());
		dto.setCpf(faker.number().digits(11));

		// data nascimento
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fakeDataNascimento = sdf.parse("1985-05-23");
		dto.setDataNascimento(fakeDataNascimento);

		EnderecoRequestDto enderecoDto = new EnderecoRequestDto();
		enderecoDto.setLogradouro(faker.address().streetName());
		enderecoDto.setComplemento(faker.address().secondaryAddress());
		enderecoDto.setNumero(faker.address().buildingNumber());
		enderecoDto.setBairro(faker.address().cityName());
		enderecoDto.setCidade(faker.address().city());
		enderecoDto.setUf(faker.address().stateAbbr());
		enderecoDto.setCep(faker.address().zipCode());

		dto.setEndereco(enderecoDto);

		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // Configuração para não usar timestamps
	    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd")); // Define o formato da data
	    String jsonContent = mapper.writeValueAsString(dto);

		mockMvc.perform(post("/api/clientes/criar").contentType("application/json").content(jsonContent))
				.andExpect(status().isCreated());

		nome = dto.getNome();
		email = dto.getEmail();
		cpf = dto.getCpf();
		dataNascimento = sdf.format(dto.getDataNascimento());
		logradouro = enderecoDto.getLogradouro();
		complemento = enderecoDto.getComplemento();
		numero = enderecoDto.getNumero();
		bairro = enderecoDto.getBairro();
		cidade = enderecoDto.getCidade();
		uf = enderecoDto.getUf();
		cep = enderecoDto.getCep();

	}

	@Test
	@Order(2)
	public void quandoAtualizarCliente_EntaoRetornar200() throws Exception {
		
		UUID id = UUID.fromString("fe31b24e-446c-483a-89b0-086364a94efb");
		
		ClienteRequestDto dto = new ClienteRequestDto();
		dto.setNome("Gabriele Monteiro");
		dto.setEmail("monteiro@hotmail.com");
		dto.setCpf("13166699985");
		
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		Date novaDataNascimento = sdf.parse("2001-12-26");
		dto.setDataNascimento(novaDataNascimento);
		
		EnderecoRequestDto enderecoDto = new EnderecoRequestDto();
		enderecoDto.setLogradouro("Rua Silvana Ribeiro");
		enderecoDto.setComplemento("Casa 1254");
		enderecoDto.setNumero("2");
		enderecoDto.setBairro("Magalhães Silvas");
		enderecoDto.setCidade("Juazeiro");
		enderecoDto.setUf("PE");
		enderecoDto.setCep("12365891");
		
		dto.setEndereco(enderecoDto);
		
		// Serializa o DTO em JSON
	    String jsonContent = objectMapper.writeValueAsString(dto);
		
		
		// Atualizar o cliente
	    mockMvc.perform(put("/api/clientes/" + id)
	            .contentType("application/json")
	            .content(jsonContent))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.nome").value("Gabriele Monteiro"))
	            .andExpect(jsonPath("$.email").value("monteiro@hotmail.com"));
	}
	
	@Test
	@Order(3)
	public void quandoBuscarClientesPorNome_EntaoRetornar200() throws Exception {
		mockMvc.perform(get("/api/clientes")
	            .contentType("application/json"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0].nome").value("Antonio Silveira")) 
	            .andExpect(jsonPath("$[1].nome").value("Antonio Silveira"));
	}
	
	@Test
	@Order(4)
	public void quandoBuscarClientesPorId_EntaoRetornar200() throws Exception {

		UUID id = UUID.fromString("1d24628d-5df4-416a-8a2e-74278136e740");

				mockMvc.perform(get("/api/clientes/buscar/" + id)
			            .contentType("application/json"))
			            .andExpect(status().isOk())
			            .andExpect(jsonPath("$.id").value(id.toString()))
			            .andExpect(jsonPath("$.nome").value("Antonio Silveira"));
	}
	
	@Test
	@Order(5)
	public void quandoExcluirCliente_EntaoRetornar204() throws Exception {
	
		UUID id = UUID.fromString("c3d6fd71-3a5a-4749-8483-dabf826b38c2");
		
		mockMvc.perform(delete("/api/clientes/excluir/" + id)
	            .contentType("application/json"))
	            .andExpect(status().isNoContent());
	}

}
