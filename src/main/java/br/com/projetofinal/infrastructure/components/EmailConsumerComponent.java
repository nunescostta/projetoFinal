package br.com.projetofinal.infrastructure.components;

import java.time.LocalTime;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetofinal.domain.dtos.EmailDto;
import br.com.projetofinal.domain.entities.LogMensagem;
import br.com.projetofinal.infrastructure.repository.LogMensagemRepository;

@Component
public class EmailConsumerComponent {

	@Autowired
	private EmailComponent emailComponent;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private LogMensagemRepository logMensagemRepository;

	// metodo para processar conteudo da fila quando projeto estiver rodando
	@RabbitListener(queues =  {"${queue.name}"} )
	public void proccessMessage(@Payload String message) {

		LogMensagem logMensagem = new LogMensagem();
		logMensagem.setId(UUID.randomUUID());
		logMensagem.setMensagem(message);
		logMensagem.setDataHora(LocalTime.now());
		
		try {

			EmailDto dto = objectMapper.readValue(message, EmailDto.class);

			emailComponent.send(dto);
			
			logMensagem.setStatus("SUCESSO");

		} catch (Exception e) {
			logMensagem.setStatus("ERRO");
			logMensagem.setErro(e.getMessage());
		}
		finally {
			//gravar log banco
			logMensagemRepository.save(logMensagem);
		}

	}
}
