package com.maaz.SpringBoot_EcomerceApp;

import com.maaz.SpringBoot_EcomerceApp.ProductService.ProductService;
import com.maaz.SpringBoot_EcomerceApp.model.Product;
import com.maaz.SpringBoot_EcomerceApp.model.SuccessResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("hello")
    public String test(){
        return "chal gya";
    }

    @GetMapping("allProduct")
    public ResponseEntity<List<Product>>  getAllProduct(){
        return new ResponseEntity<>(service.getAllProducts() , HttpStatus.ACCEPTED);
    }

    @PostMapping("product")
    public ResponseEntity<? > addProduct(@RequestPart  Product addProduct, @RequestPart MultipartFile image){
        try {
            return new ResponseEntity<>(service.addProduct(null,addProduct,image),HttpStatus.ACCEPTED) ;
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        return new ResponseEntity<>(service.getProduct(productId),HttpStatus.ACCEPTED);

    }

    //Update Product
    @PutMapping("product/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer productId,@RequestPart Product product, @RequestPart MultipartFile image){
        try {
            return new ResponseEntity<>( service.addProduct(productId,product,image),HttpStatus.ACCEPTED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete Product
    @DeleteMapping("product/{productId}")
    public ResponseEntity<SuccessResponse> deleteProduct(@PathVariable Integer productId){
        service.deleteProduct(productId);
        return new ResponseEntity<>(new SuccessResponse(true,true),HttpStatus.ACCEPTED);
    }

    @GetMapping("products/{search}")
    public ResponseEntity<List<Product>> searchByKeyword(@PathVariable String search){
        return new ResponseEntity<>(service.seatchProduct(search),HttpStatus.ACCEPTED);
    }




}
