package com.melita.orderingfulfilment.modules.email.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;

@Getter
@Builder
public class EmailMessage {
    @Singular
    private Set<String> recipients;
    private String subject;
    private String message;
}