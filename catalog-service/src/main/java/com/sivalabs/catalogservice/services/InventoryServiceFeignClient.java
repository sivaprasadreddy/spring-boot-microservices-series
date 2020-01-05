package com.sivalabs.catalogservice.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.sivalabs.catalogservice.services.fallback.InventoryServiceFeignClientFallBackFactory;
import com.sivalabs.catalogservice.web.models.ProductInventoryResponse;

@FeignClient(name = "inventory-service", fallbackFactory = InventoryServiceFeignClientFallBackFactory.class)
public interface InventoryServiceFeignClient {

  @GetMapping("/api/inventory")
  List<ProductInventoryResponse> getInventoryLevels();

  @GetMapping("/api/inventory/{productCode}")
  List<ProductInventoryResponse> getInventoryByProductCode(String productCode);

}
