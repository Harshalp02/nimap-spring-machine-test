package com.nimap.machine_test.dto;

import lombok.Data;
import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products; // List of products belonging to this category
}
