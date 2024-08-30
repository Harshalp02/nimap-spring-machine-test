package com.nimap.machine_test.repository;

import com.nimap.machine_test.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category,Long>{
}
