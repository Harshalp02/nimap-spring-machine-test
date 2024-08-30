package com.nimap.machine_test.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private CategoryDTO category; // Optionally include category details
}
