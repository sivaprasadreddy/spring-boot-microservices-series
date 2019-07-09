package com.sivalabs.catalogservice.services.impl;

import com.sivalabs.catalogservice.entities.Product;
import com.sivalabs.catalogservice.repositories.ProductRepository;
import com.sivalabs.catalogservice.services.InventoryServiceClient;
import com.sivalabs.catalogservice.services.ProductService;
import com.sivalabs.catalogservice.web.models.ProductInventoryResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  
    private final ProductRepository productRepository;
    private final InventoryServiceClient inventoryServiceClient;

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = productRepository.findAll();
        final Map<String, Integer> inventoryLevels = getInventoryLevelsWithFeignClient();
        return products.stream()
                .filter(p -> inventoryLevels.get(p.getCode()) != null && inventoryLevels.get(p.getCode()) > 0)
                .collect(Collectors.toList());
    }

    private Map<String, Integer> getInventoryLevelsWithFeignClient() {
        log.info("Fetching inventory levels using FeignClient");
        Map<String, Integer> inventoryLevels = new HashMap<>();
        List<ProductInventoryResponse> inventory = inventoryServiceClient.getProductInventoryLevels();
        for (ProductInventoryResponse item: inventory){
            inventoryLevels.put(item.getProductCode(), item.getAvailableQuantity());
        }
        log.debug("InventoryLevels: {}", inventoryLevels);
        return inventoryLevels;
    }

    @Override
    public Optional<Product> findProductByCode(String code) {
        Optional<Product> productOptional = productRepository.findByCode(code);
        if (productOptional.isPresent()) {
            log.info("Fetching inventory level for product_code: {}" , code);
            Optional<ProductInventoryResponse> itemResponseEntity =
                    this.inventoryServiceClient.getProductInventoryByCode(code);
            if (itemResponseEntity.isPresent()) {
                Integer quantity = itemResponseEntity.get().getAvailableQuantity();
                productOptional.get().setInStock(quantity > 0);
            }
        }
        return productOptional;
    }
}
