package com.melita.orderingfulfilment.modules.order.amqp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonDeserialize
public record CustomerDetailsDTO (
    Long userId,
    String userName,
    String email,
    String document,
    String installationAddress,
    @JsonDeserialize(using = LocalDateDeserializer.class) LocalDate installationDate,
    @JsonDeserialize(using = LocalTimeDeserializer.class) LocalTime startTime,
    @JsonDeserialize(using = LocalTimeDeserializer.class) LocalTime endTime
) {}
