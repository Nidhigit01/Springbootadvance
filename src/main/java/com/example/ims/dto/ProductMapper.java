package com.example.ims.dto;

import com.example.ims.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    
    public ProductResponseDto convertToResponseDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setName(product.getName());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setStockQuantity(product.getStockQuantity());
        return responseDto;
    }

    public Product convertToEntity(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());
        product.setStockQuantity(requestDto.getStockQuantity());
        return product;
    }
    
}
