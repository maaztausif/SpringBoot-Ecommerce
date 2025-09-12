package com.maaz.SpringBoot_EcomerceApp.model.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
        String productName,
        int quantity,
        BigDecimal subTotal
) {
}
