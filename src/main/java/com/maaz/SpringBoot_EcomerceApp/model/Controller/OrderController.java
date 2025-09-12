package com.maaz.SpringBoot_EcomerceApp.model.Controller;

import com.maaz.SpringBoot_EcomerceApp.ProductService.OrderService;
import com.maaz.SpringBoot_EcomerceApp.model.dto.OrderItemResponse;
import com.maaz.SpringBoot_EcomerceApp.model.dto.OrderRequest;
import com.maaz.SpringBoot_EcomerceApp.model.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("order/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = service.placeOrder(orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("orders")
    public ResponseEntity<List<OrderResponse>> getAllOrderResponse(){
        List<OrderResponse> responses = service.getAllOrders();
        return new ResponseEntity<>(responses,HttpStatus.ACCEPTED);

    }
}
