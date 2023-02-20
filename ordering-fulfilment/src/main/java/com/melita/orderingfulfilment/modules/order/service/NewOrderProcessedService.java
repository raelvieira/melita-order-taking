package com.melita.orderingfulfilment.modules.order.service;

import com.melita.orderingfulfilment.modules.order.amqp.dto.NewOrderProcessedDTO;
import jakarta.mail.MessagingException;

public interface NewOrderProcessedService {
    void execute(NewOrderProcessedDTO newOrderProcessed) throws MessagingException;
}
