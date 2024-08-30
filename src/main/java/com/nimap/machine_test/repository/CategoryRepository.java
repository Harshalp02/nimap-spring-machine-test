package com.nimap.machine_test.repository;

import com.nimap.machine_test.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository <Category,Long>{

}
