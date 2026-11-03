package com.example.ims.service;

import java.util.Optional;

import com.example.ims.dto.ProductMapper;
import com.example.ims.dto.ProductRequestDto;
import com.example.ims.repository.ProductRepository;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.ims.entity.Product;
import com.example.ims.dto.ProductResponseDto;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {
    
    @Mock
    private ProductRepository productRepo;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateProduct(){

        ProductRequestDto request = new ProductRequestDto();

        request.setName("Laptop");
        request.setDescription("Dell Laptop");
        request.setPrice(65000);
        request.setStockQuantity(10);

        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("Dell Laptop");
        product.setPrice(65000);
        product.setStockQuantity(10);

        ProductResponseDto response = new ProductResponseDto();
        response.setName("Laptop");
        

        when(productMapper.convertToEntity(request)).thenReturn(product);
        when(productRepo.save(product)).thenReturn(product);
        when(productMapper.convertToResponseDto(product)).thenReturn(response);

        ProductResponseDto result = productService.createProduct(request);

        assertNotNull(result);

        assertEquals("Laptop", result.getName());

        verify(productRepo).save(product);

    }

    @Test
    public void shouldReturnProductById(){

        Product product = new Product();

        product.setName("Laptop");
        
        
        ProductResponseDto response = new ProductResponseDto();
        response.setId(1L);
        response.setName("Laptop");

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.convertToResponseDto(product)).thenReturn(response);

        ProductResponseDto result = productService.getProductById(1L);
        assertEquals("Laptop", result.getName());

    }

    @Test 
    public void shouldThrowExceptionWhenProductNotFound(){

        when(productRepo.findById(10L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.getProductById(10L));
    }

    @Test 
    public void shouldUpdateProduct(){

        ProductRequestDto request = new ProductRequestDto();

        request.setName("Updated Laptop");
        request.setDescription("Updated Description");
        request.setPrice(70000);
        request.setStockQuantity(5);

        Product existing = new Product();

        existing.setName("Old Laptop");

        ProductResponseDto response = new ProductResponseDto();
        response.setId(1L);
        response.setName("Updated Laptop");

        when(productRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(productRepo.save(any(Product.class))).thenReturn(existing);
        when(productMapper.convertToResponseDto(any(Product.class))).thenReturn(response);
        
        ProductResponseDto result = productService.updateProduct(1L, request);

        assertEquals("Updated Laptop", result.getName());

    }

    @Test 
    public void shouldDeleteProduct(){

        when(productRepo.existsById(1L)).thenReturn(true);

        productService.deleteProduct(1L);

        verify(productRepo).deleteById(1L);
    }

    @Test 
    public void shouldThrowExceptionWhenDeletingNonExistingProduct(){

        when(productRepo.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> productService.deleteProduct(1L));
    }
}
