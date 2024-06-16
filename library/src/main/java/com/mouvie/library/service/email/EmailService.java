package com.mouvie.library.service.email;


import com.mouvie.library.dto.email.EmailTemplate;
import com.mouvie.library.tools.constants.EmailConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    private final EmailConstants emailConstants;

    public void sendSimpleMessage(EmailTemplate emailTemplate) {

        log.info("Sending email to {}", emailTemplate.getTo());
        log.info("Body: {}", emailTemplate.getBody());

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailConstants.noReply);
        message.setTo(emailTemplate.getTo());
        message.setSubject(emailTemplate.getSubject());
        message.setText(emailTemplate.getBody());

        emailSender.send(message);

    }
}
