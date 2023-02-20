package com.melita.orderingfulfilment.modules.email.service.impl;

import com.melita.orderingfulfilment.modules.email.model.EmailMessage;
import com.melita.orderingfulfilment.modules.email.service.SendEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SmtpSendEmailServiceImpl implements SendEmailService {
    private final JavaMailSender mailSender;

    @Value("${email.sender}")
    private String emailSender;

    public SmtpSendEmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(EmailMessage message) throws MessagingException {
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        helper.setFrom(this.emailSender);
        helper.setTo(message.getRecipients().toArray(new String[0]));
        helper.setSubject(message.getSubject());
        helper.setText(message.getMessage(), true);

        this.mailSender.send(mimeMessage);
    }
}
