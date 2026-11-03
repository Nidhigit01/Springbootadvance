package com.example.ims.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ims.dto.ProductRequestDto;
import com.example.ims.dto.ProductResponseDto;
import com.example.ims.service.ProductService;

@Controller 
public class ProductViewController {
    
    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getProducts(Model model){
        model.addAttribute("products", productService.getAllProducts(PageRequest.of(0, 100)));
        return "products";
    }

    @GetMapping("/products/new")
    public String showForm(Model model){
        model.addAttribute("product", new ProductRequestDto());

        return "product-form";
    }

    @PostMapping("products/save")
    public String saveProduct(ProductRequestDto request){

        productService.createProduct(request);
        return "redirect:/products";
    }

    @GetMapping ("/products/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        
        ProductResponseDto product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable Long id, ProductRequestDto request){

        productService.updateProduct(id, request);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id){

        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
