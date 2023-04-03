package com.enigmacamp.TestDDL.repository;

import com.enigmacamp.TestDDL.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
