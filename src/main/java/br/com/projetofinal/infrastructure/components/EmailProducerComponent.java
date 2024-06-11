package br.com.projetofinal.infrastructure.components;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetofinal.domain.dtos.EmailDto;

@Component
public class EmailProducerComponent {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private Queue queue;

	// metodo que ira receber o dto contendo mensagem e enviar para fila
	public void sendMessage(EmailDto dto) throws Exception {

		// transformando os dados dto para JSON
		String data = objectMapper.writeValueAsString(dto);

		// gravando mensagem na fila
		rabbitTemplate.convertAndSend(queue.getName(), data);

	}

}
