package com.maaz.SpringBoot_EcomerceApp.ProductService;

import com.maaz.SpringBoot_EcomerceApp.ProductRepo.OrderRepo;
import com.maaz.SpringBoot_EcomerceApp.ProductRepo.ProductRepo;
import com.maaz.SpringBoot_EcomerceApp.model.Order;
import com.maaz.SpringBoot_EcomerceApp.model.OrderItem;
import com.maaz.SpringBoot_EcomerceApp.model.Product;
import com.maaz.SpringBoot_EcomerceApp.model.dto.OrderItemRequest;
import com.maaz.SpringBoot_EcomerceApp.model.dto.OrderItemResponse;
import com.maaz.SpringBoot_EcomerceApp.model.dto.OrderRequest;
import com.maaz.SpringBoot_EcomerceApp.model.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepo productRepo;

    @Autowired
    OrderRepo orderRepo;
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = new Order();
        String orderId = "ORD"+ UUID.randomUUID().toString().substring(0,8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(request.customerName());
        order.setEmail(request.email());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());


        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItemRequest itemRequest : request.items()){
            Product product = productRepo.findById(itemRequest.productID()).orElseThrow(() -> new RuntimeException("Product Not Found"));
            product.setStockQuantity(product.getStockQuantity()-itemRequest.quantity());
            productRepo.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemRequest.quantity())
                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.quantity())))
                    .order(order)
                    .build();

            orderItems.add(orderItem);


        }

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepo.save(order);

        List<OrderItemResponse> itemResponses = new ArrayList<>();

        for(OrderItem items : order.getOrderItems()){
            OrderItemResponse orderItemResponses = new OrderItemResponse(
                    items.getProduct().getName(),
                    items.getQuantity(),
                    items.getTotalPrice()
            );
            itemResponses.add(orderItemResponses);

        }

        OrderResponse orderResponse = new OrderResponse(
                savedOrder.getOrderId(),
                savedOrder.getCustomerName(),
                savedOrder.getEmail(),
                savedOrder.getStatus(),
                savedOrder.getOrderDate(),
                itemResponses
        );
        return orderResponse;

    }

    public List<OrderResponse> getAllOrders() {
        List<Order> allOrders = orderRepo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for(Order order:allOrders){

            List<OrderItemResponse> itemResponses = new ArrayList<>();

            for(OrderItem orderItem:order.getOrderItems()){
                OrderItemResponse orderItemResponse = new OrderItemResponse(
                        orderItem.getProduct().getName(),
                        orderItem.getQuantity(),
                        orderItem.getTotalPrice()
                );
                itemResponses.add(orderItemResponse);
            }

            OrderResponse orderResponse = new OrderResponse(
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getEmail(),
                    order.getStatus(),
                    order.getOrderDate(),
                    itemResponses
            );

            orderResponses.add(orderResponse);
        }
        return  orderResponses;
    }
}
