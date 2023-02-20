package com.melita.orderingfulfilment.modules.order.amqp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public record ProductsDTO(
    String product,
    String packageProduct
) {}