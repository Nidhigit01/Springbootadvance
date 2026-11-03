package com.example.ims.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ims.dto.ProductResponseDto;
import com.example.ims.dto.ProductRequestDto;
import com.example.ims.entity.Product;
import com.example.ims.service.ProductService;

@RestController 
@RequestMapping("/api/products")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ProductResponseDto getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<ProductResponseDto> getAllProduct(Pageable pageable){
        return productService.getAllProducts(pageable);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable Long id){
         productService.deleteProduct(id);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<ProductResponseDto> searchProducts(@RequestParam String name){
        return productService.searchProducts(name);
    }


}
