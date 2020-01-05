package com.sivalabs.catalogservice.web.controllers;

import com.sivalabs.catalogservice.entities.Product;
import com.sivalabs.catalogservice.exceptions.ProductNotFoundException;
import com.sivalabs.catalogservice.services.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> allProducts(HttpServletRequest request) {
        log.info("Finding all products");
        String authHeader = request.getHeader("AUTH_HEADER");
        log.info("AUTH_HEADER: {}", authHeader);
        return productService.findAllProducts();
    }

    @GetMapping("/{code}")
    public Product productByCode(@PathVariable String code) {
        log.info("Finding product by code : {}",code);
        return productService.findProductByCode(code)
                .orElseThrow(() -> new ProductNotFoundException("Product with code ["+code+"] doesn't exist"));
    }
}
