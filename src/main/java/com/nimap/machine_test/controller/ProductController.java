package com.nimap.machine_test.controller;

import com.nimap.machine_test.dto.ProductDTO;
import com.nimap.machine_test.dto.CategoryDTO;
import com.nimap.machine_test.entity.Product;
import com.nimap.machine_test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET all products with pagination
    @GetMapping
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productService.getAllProducts(pageable).map(this::convertToDTO);
    }

    // GET a single product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(p -> ResponseEntity.ok(convertToDTO(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - create a new product
    @PostMapping
    public ProductDTO createProduct(@RequestBody Product product) {
        return convertToDTO(productService.createProduct(product));
    }

    // PUT - update product by ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        if (updatedProduct != null) {
            return ResponseEntity.ok(convertToDTO(updatedProduct));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - delete product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());

        if (product.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(product.getCategory().getId());
            categoryDTO.setName(product.getCategory().getName());
            dto.setCategory(categoryDTO);
        }

        return dto;
    }
}
