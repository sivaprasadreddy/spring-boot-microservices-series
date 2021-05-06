package com.sivalabs.catalogservice.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sivalabs.catalogservice.web.models.ProductInventoryResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceClient {
  
    private final RestTemplate restTemplate;
    private final InventoryServiceFeignClient inventoryServiceFeignClient;
    
    //TODO; move this to config file
    private static final String INVENTORY_API_PATH = "http://inventory-service/api/";


    public List<ProductInventoryResponse> getProductInventoryLevels() {
        return this.inventoryServiceFeignClient.getInventoryLevels();
    }


    @CircuitBreaker(name = "default", fallbackMethod = "getDefaultProductInventoryByCode")
    public Optional<ProductInventoryResponse> getProductInventoryByCode(String productCode)
    {
        ResponseEntity<ProductInventoryResponse> itemResponseEntity =
                restTemplate.getForEntity(INVENTORY_API_PATH + "inventory/{code}",
                        ProductInventoryResponse.class,
                        productCode);

        
        //Simulate Delay
        try {
            java.util.concurrent.TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        

        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            Integer quantity = Objects.requireNonNull(itemResponseEntity.getBody()).getAvailableQuantity();
            log.info("Available quantity: " + quantity);
            return Optional.ofNullable(itemResponseEntity.getBody());
        } else {
            log.error("Unable to get inventory level for product_code: {}, StatusCode: {}", productCode, itemResponseEntity.getStatusCode());
            return Optional.empty();
        }
    }

    Optional<ProductInventoryResponse> getDefaultProductInventoryByCode(String productCode) {
        log.info("Returning default ProductInventoryByCode for productCode: {}",productCode);
        ProductInventoryResponse response = new ProductInventoryResponse();
        response.setProductCode(productCode);
        response.setAvailableQuantity(50);
        return Optional.of(response);
    }

}
