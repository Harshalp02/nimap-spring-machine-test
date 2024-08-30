package com.nimap.machine_test.repository;

import com.nimap.machine_test.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
