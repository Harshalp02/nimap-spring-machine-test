package com.nimap.machine_test.controller;

import com.nimap.machine_test.dto.CategoryDTO;
import com.nimap.machine_test.dto.ProductDTO;
import com.nimap.machine_test.entity.Category;
import com.nimap.machine_test.entity.Product;
import com.nimap.machine_test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // GET all categories with pagination
    @GetMapping
    public Page<CategoryDTO> getAllCategories(Pageable pageable) {
        return categoryService.getAllCategories(pageable).map(this::convertToDTO);
    }

    // GET a single category by ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(c -> ResponseEntity.ok(convertToDTO(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - create a new category
    @PostMapping
    public CategoryDTO createCategory(@RequestBody Category category) {
        return convertToDTO(categoryService.createCategory(category));
    }

    // PUT - update category by ID
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Category updatedCategory = categoryService.updateCategory(id, categoryDetails);
        if (updatedCategory != null) {
            return ResponseEntity.ok(convertToDTO(updatedCategory));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - delete category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        // Convert products to DTOs if needed
        if (category.getProducts() != null) {
            dto.setProducts(category.getProducts().stream()
                    .map(this::convertProductToDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private ProductDTO convertProductToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());

        // Optionally include category details, but avoid recursion
        if (product.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(product.getCategory().getId());
            categoryDTO.setName(product.getCategory().getName());
            dto.setCategory(categoryDTO);
        }

        return dto;
    }
}
