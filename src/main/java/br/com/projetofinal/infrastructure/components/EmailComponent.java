package br.com.projetofinal.infrastructure.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import br.com.projetofinal.domain.dtos.EmailDto;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailComponent {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String userName;

	public void send(EmailDto dto) throws Exception {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

		helper.setFrom(userName);
		helper.setTo(dto.getDestinatario());
		helper.setSubject(dto.getAssunto());
		helper.setText(dto.getMensagem());

		javaMailSender.send(mimeMessage);

	}
}
