package com.maaz.SpringBoot_EcomerceApp.ProductRepo;

import com.maaz.SpringBoot_EcomerceApp.model.Product;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
}
