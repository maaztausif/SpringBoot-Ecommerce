package com.maaz.SpringBoot_EcomerceApp.ProductService;

import com.maaz.SpringBoot_EcomerceApp.ProductRepo.ProductRepo;
import com.maaz.SpringBoot_EcomerceApp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product addProduct(Product addProduct) {
        repo.save(addProduct);
        return repo.findById(addProduct.getId()).orElse(new Product());
    }

    public Product getProduct(Integer productId) {
        return repo.findById(productId).orElse(new Product());
    }
}
