package com.melita.orderingfulfilment.modules.order.service.impl;

import com.melita.orderingfulfilment.modules.email.model.EmailMessage;
import com.melita.orderingfulfilment.modules.email.service.SendEmailService;
import com.melita.orderingfulfilment.modules.order.amqp.dto.NewOrderProcessedDTO;
import com.melita.orderingfulfilment.modules.order.model.Order;
import com.melita.orderingfulfilment.modules.order.repository.OrderRepository;
import com.melita.orderingfulfilment.modules.order.service.NewOrderProcessedService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NewOrderProcessedServiceImpl implements NewOrderProcessedService {
    private final OrderRepository orderRepository;

    private final SendEmailService emailService;

    @Value("${email.agent.receiving")
    private String agentEmail;

    public NewOrderProcessedServiceImpl(OrderRepository orderRepository, SendEmailService emailService) {
        this.orderRepository = orderRepository;
        this.emailService = emailService;
    }

    @Override
    public void execute(NewOrderProcessedDTO newOrderProcessed) throws MessagingException {
        Order order = newOrderProcessed.toOrder();
        this.orderRepository.save(order);

        /*
        this.emailService.send(EmailMessage.builder()
            .subject("New Order")
            .recipient(agentEmail)
            .message("New order received")
            .build()
        );
        */
    }
}
