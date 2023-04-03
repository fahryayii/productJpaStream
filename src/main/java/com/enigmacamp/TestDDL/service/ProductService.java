package com.enigmacamp.TestDDL.service;

import com.enigmacamp.TestDDL.entity.Product;
import com.enigmacamp.TestDDL.entity.Product$;
import com.enigmacamp.TestDDL.repository.ProductRepository;
import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private JPAStreamer jpaStreamer;

    public List<Product> saveProducts(List<Product> products){
        return productRepository.saveAll(products);
    }

    public List<Product> getProducts(int offset, int limit){
        return jpaStreamer.stream(Product.class)
                .sorted(Product$.name) // Sort by Name
                .skip(offset) // Pagination
                .limit(limit) // Pagination
                .collect(Collectors.toList());
    }
    public List<Product> getProductByType(String dept){
        return jpaStreamer.stream(Product.class)
                .filter(Product$.type.equal(dept))
                .collect(Collectors.toList());
    }

    public List<Product> getProductByTypeAndPrice(String dept, Double price){
        return jpaStreamer.stream(Product.class)
                .filter(Product$.type.equal(dept)
                        .and(Product$.price.greaterOrEqual(price)))
                .collect(Collectors.toList());
    }

    public List<Product> getProductByPriceRange(Double price1, Double price2){
        return  jpaStreamer.stream(Product.class)
                .filter(Product$.price.between(price1, price2))
                .collect(Collectors.toList());
    }

    //aggregate fun

    public Product minPaidProduct(){
        return  jpaStreamer.stream(Product.class)
                .min(Comparator.comparing(Product::getPrice)).get();
    }

    public List<Product> getProductByIds(List<Long> ids){
        return jpaStreamer.stream(Product.class)
                .filter(Product$.id.in(ids))
                .collect(Collectors.toList());
    }

    public Map<String, List<Product>> getProductByType(){
        return jpaStreamer.stream(Product.class)
                .collect(Collectors.groupingBy(Product::getType));
    }


}
