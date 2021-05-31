package com.wani.pojotutorial.components.model;

import java.io.Serializable;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
public class OrderInfo implements Serializable {

    private UUID orderId;
    private String coffee;

    @Builder
    public OrderInfo(UUID orderId, String coffee) {
        this.orderId = orderId;
        this.coffee = coffee;
    }
}
