package com.nimap.machine_test.service;

import com.nimap.machine_test.entity.Category;
import com.nimap.machine_test.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Fetch all categories with pagination
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    // Fetch a single category by ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Create a new category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Update an existing category
    public Category updateCategory(Long id, Category categoryDetails) {
        Optional<Category> categoryOptional = getCategoryById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setName(categoryDetails.getName());  // Assuming Category has a 'name' field
            return categoryRepository.save(category);
        }
        return null; // Handle this case as needed
    }

    // Delete a category by ID
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
