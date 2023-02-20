package com.melita.orderingfulfilment.modules.order.amqp;

import com.melita.orderingfulfilment.modules.order.amqp.dto.NewOrderProcessedDTO;
import com.melita.orderingfulfilment.modules.order.service.NewOrderProcessedService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NewOrderProcessedListener {
    private static final Logger LOG = LoggerFactory.getLogger(NewOrderProcessedListener.class);

    private final NewOrderProcessedService processedService;

    public NewOrderProcessedListener(NewOrderProcessedService processedService) {
        this.processedService = processedService;
    }

    @RabbitListener(queues = "new.order")
    public void processNewOrderProcessed(NewOrderProcessedDTO newOrderProcessed) throws MessagingException {
        LOG.info("Message received {}", newOrderProcessed);
        this.processedService.execute(newOrderProcessed);
    }
}
