package com.maaz.SpringBoot_EcomerceApp.ProductService;

import com.maaz.SpringBoot_EcomerceApp.ProductRepo.ProductRepo;
import com.maaz.SpringBoot_EcomerceApp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }



    public Product addProduct(Integer productId, Product addProduct) {
        if (productId != null){
            Optional<Product> updatingProduct = repo.findById(productId);
            if (updatingProduct.isPresent()){
                Product product = updatingProduct.get();

                product.setId(productId);
                product.setProductAvailable(addProduct.getProductAvailable());
                product.setName(addProduct.getName());
                product.setBrand(addProduct.getBrand());
                product.setPrice(addProduct.getPrice());
                product.setCategory(addProduct.getCategory());
                product.setDescription(addProduct.getDescription());
                product.setReleaseDate(addProduct.getReleaseDate());
                product.setStockQuantity(addProduct.getStockQuantity());

                repo.save(product);
            }

        }else{
            repo.save(addProduct);
        }
        return repo.findById(productId == null? addProduct.getId():productId).orElse(new Product());
    }

    public Product getProduct(Integer productId) {
        return repo.findById(productId).orElse(new Product());
    }



}
