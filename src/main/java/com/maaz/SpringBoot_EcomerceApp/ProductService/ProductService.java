package com.maaz.SpringBoot_EcomerceApp.ProductService;

import com.maaz.SpringBoot_EcomerceApp.ProductRepo.ProductRepo;
import com.maaz.SpringBoot_EcomerceApp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }



    public Product addProduct(Integer productId, Product addProduct , MultipartFile image) throws IOException {
        if (productId != null){
            Optional<Product> updatingProduct = repo.findById(productId);
            if (updatingProduct.isPresent()){
                Product product = updatingProduct.get();

                //For Update Dta
                product.setId(productId);
                product.setProductAvailable(addProduct.getProductAvailable());
                product.setName(addProduct.getName());
                product.setBrand(addProduct.getBrand());
                product.setPrice(addProduct.getPrice());
                product.setCategory(addProduct.getCategory());
                product.setDescription(addProduct.getDescription());
                product.setReleaseDate(addProduct.getReleaseDate());
                product.setStockQuantity(addProduct.getStockQuantity());

                //For image update
                if(image.isEmpty()){
                    product.setImageData(addProduct.getImageData());
                    product.setImageType(addProduct.getImageType());
                    product.setImageName(addProduct.getImageName());
                }else{
                    product.setImageData(image.getBytes());
                    product.setImageType(image.getContentType());
                    product.setImageName(image.getName());
                }
                repo.save(product);
            }
        }else{
            //upload data for the first time
            addProduct.setImageName(image.getName());
            addProduct.setImageType(image.getContentType());
            addProduct.setImageData(image.getBytes());
            repo.save(addProduct);
        }
        return repo.findById(productId == null? addProduct.getId():productId).orElse(new Product());
    }

    public Product getProduct(Integer productId) {
        return repo.findById(productId).orElse(new Product());
    }

    public void deleteProduct(Integer productId) {
        repo.deleteById(productId);
    }

    public List<Product> seatchProduct(String keyword) {
        return repo.searchProductByKeyword(keyword);
    }
}
