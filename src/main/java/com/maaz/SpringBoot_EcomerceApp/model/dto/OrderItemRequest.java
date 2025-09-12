package com.maaz.SpringBoot_EcomerceApp.model.dto;

public record OrderItemRequest(
        int productID,
        int quantity
) {
}
