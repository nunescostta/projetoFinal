package br.com.projetofinal.infrastructure.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.projetofinal.domain.dtos.ClienteResponseDto;
import br.com.projetofinal.domain.entities.Cliente;

@Configuration
public class ModelMapperConfig {
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		// Configurando o mapeamento de String para LocalDate
		modelMapper.createTypeMap(String.class, LocalDate.class).setConverter(context -> {
			String dateString = context.getSource();
			if (dateString == null) {
				return null;
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return LocalDate.parse(dateString, formatter);
		});
		// Configurando o mapeamento de String para LocalTime
		modelMapper.createTypeMap(String.class, LocalTime.class).setConverter(context -> {
			String timeString = context.getSource();
			if (timeString == null) {
				return null;
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			return LocalTime.parse(timeString, formatter);
		});

		// configurando para gerar UUID cliente
		modelMapper.createTypeMap(Cliente.class, ClienteResponseDto.class).addMappings(mapper -> {
			mapper.map(Cliente::getIdCliente, ClienteResponseDto::setId);

		});
		return modelMapper;
	}
}
