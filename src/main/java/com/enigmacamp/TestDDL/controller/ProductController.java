package com.enigmacamp.TestDDL.controller;

import com.enigmacamp.TestDDL.entity.Product;
import com.enigmacamp.TestDDL.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public List<Product> saveProducts(@RequestBody List<Product> products){
        return productService.saveProducts(products);
    }
    @GetMapping("/{offset}/{limit}")
    public List<Product> getProducts(@PathVariable int offset, @PathVariable int limit){
        return productService.getProducts(offset, limit);
    }
    @GetMapping("/{type}")
    public List<Product> getProductByType(@PathVariable String type) {
        return productService.getProductByType(type);
    }

    @GetMapping("/fetch/{type}/{price}")
    public List<Product> getProductByTypeAndPrice(@PathVariable String type, @PathVariable Double price) {
        return productService.getProductByTypeAndPrice(type,price);
    }

    @GetMapping("/range/{price1}/{price2}")
    public List<Product> getProductByPriceRange(@PathVariable Double price1, @PathVariable Double price2) {
        return productService.getProductByPriceRange(price1, price2);
    }

    @GetMapping("/min")
    public Product getLessPaidProducts(){
        return productService.minPaidProduct();
    }

    @PostMapping("/ids")
    public  List<Product> getProductByIds(@RequestBody List<Long> ids){
        return productService.getProductByIds(ids);
    }

    @GetMapping("/groupByType")
    public Map<String, List<Product>> getProdcutByType(){
        return productService.getProductByType();
    }

}
