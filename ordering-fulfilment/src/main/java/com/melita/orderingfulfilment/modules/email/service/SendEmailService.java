package com.melita.orderingfulfilment.modules.email.service;

import com.melita.orderingfulfilment.modules.email.model.EmailMessage;
import jakarta.mail.MessagingException;

public interface SendEmailService {
    void send(EmailMessage message) throws MessagingException;
}
