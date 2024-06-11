package br.com.projetofinal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
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
		dto.setCpf(faker.number().digits(14));
		
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
        

        mockMvc.perform(post("/api/clientes/post") 
        		.contentType("application/json") 
        		.content(objectMapper.writeValueAsString(dto)))
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
	public void quandoCadastrarClienteComMesmoEmail_EntaoRetornar422() throws Exception {
		mockMvc.perform(post("/api/cliente/post")).andExpect(status().isUnprocessableEntity());
	}

	@Test
	@Order(3)
	public void quandoBuscarClienteComIdInvalido_EntaoRetornar402() throws Exception {
		mockMvc.perform(put("/api/cliente/put")).andExpect(status().isUnauthorized());
	}

}
