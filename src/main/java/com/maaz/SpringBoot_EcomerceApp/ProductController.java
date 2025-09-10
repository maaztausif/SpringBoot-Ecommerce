package com.maaz.SpringBoot_EcomerceApp;

import com.maaz.SpringBoot_EcomerceApp.ProductService.ProductService;
import com.maaz.SpringBoot_EcomerceApp.model.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Product > addProduct(@RequestBody  Product addProduct){
        return new ResponseEntity<>(service.addProduct(addProduct),HttpStatus.ACCEPTED) ;
    }

    @PostMapping("product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        return new ResponseEntity<>(service.getProduct(productId),HttpStatus.ACCEPTED);

    }


}
