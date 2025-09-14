package com.maaz.SpringBoot_EcomerceApp.ProductRepo;


import com.maaz.SpringBoot_EcomerceApp.model.Order;
import com.maaz.SpringBoot_EcomerceApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer>{
    Optional<Order> findByOrderId(String orderID);
}
