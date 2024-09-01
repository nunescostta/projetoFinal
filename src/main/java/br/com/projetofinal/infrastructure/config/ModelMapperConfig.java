package br.com.projetofinal.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;
import org.modelmapper.AbstractConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Converter para formatar String (dd/MM/yyyy) para Date
        Converter<String, Date> stringToDateConverter = new AbstractConverter<String, Date>() {
            @Override
            protected Date convert(String source) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    return sdf.parse(source);
                } catch (ParseException e) {
                    throw new RuntimeException("Erro ao converter String para Date: " + source, e);
                }
            }
        };

        // Converter para formatar Date para String (dd/MM/yyyy)
        Converter<Date, String> dateToStringConverter = new AbstractConverter<Date, String>() {
            @Override
            protected String convert(Date source) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(source);
            }
        };

        // Registro dos conversores no ModelMapper
        modelMapper.addConverter(stringToDateConverter);
        modelMapper.addConverter(dateToStringConverter);

        return modelMapper;
    }
}
