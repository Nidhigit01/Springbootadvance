package com.example.ims.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ims.dto.ProductResponseDto;
import com.example.ims.dto.ProductRequestDto;
import com.example.ims.dto.ProductMapper;
import com.example.ims.entity.Product;
import com.example.ims.repository.ProductRepository;
import com.example.ims.exception.ResourceNotFoundException;

import jakarta.validation.Valid;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    private final ProductMapper productMapper;

    @Autowired 
    public ProductService(ProductRepository productRepo, ProductMapper productMapper){
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }
    
    public ProductResponseDto createProduct(@Valid @RequestBody ProductRequestDto product){
        Product savedProduct = productRepo.save(productMapper.convertToEntity(product));
        return productMapper.convertToResponseDto(savedProduct);
    }

    public ProductResponseDto getProductById(Long id){
        return productMapper.convertToResponseDto(productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with id:"+id)));
    }

    public ProductResponseDto updateProduct(Long id, @Valid @RequestBody ProductRequestDto product){
        Product existingProduct = productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with id:"+id));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());

        Product updatedProduct = productRepo.save(existingProduct);
        return productMapper.convertToResponseDto(updatedProduct);
        
    }

   
    public List<ProductResponseDto> getAllProducts(Pageable pageable){
        return productRepo.findAll(pageable).stream().map(productMapper::convertToResponseDto).collect(Collectors.toList());
    }

    public void deleteProduct(Long id){
        if(!productRepo.existsById(id)){
            throw new ResourceNotFoundException("Product not found with id:"+id);
        }

        productRepo.deleteById(id);

    }

    public List<ProductResponseDto> searchProducts(String name){
        return productRepo.findByNameContainingIgnoreCase(name).stream().map(productMapper::convertToResponseDto).collect(Collectors.toList());
    }

}
