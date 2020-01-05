package com.sivalabs.catalogservice.services.fallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sivalabs.catalogservice.services.InventoryServiceFeignClient;
import com.sivalabs.catalogservice.web.models.ProductInventoryResponse;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InventoryServiceFeignClientFallBackFactory
    implements FallbackFactory<InventoryServiceFeignClient> {

  @Override
  public InventoryServiceFeignClient create(Throwable cause) {
    return new InventoryServiceFeignClient() {

      @Override
      public List<ProductInventoryResponse> getInventoryLevels() {
        log.info("Returning default product inventory levels, reason was: "
            + cause.getMessage());
        return new ArrayList<>();
      }

      @Override
      public List<ProductInventoryResponse> getInventoryByProductCode(
          String productCode) {
        log.info("Returning default product, reason was: " + cause.getMessage());
        ProductInventoryResponse productInventoryResponse = new ProductInventoryResponse();
        productInventoryResponse.setProductCode(productCode);
        productInventoryResponse.setAvailableQuantity(50);
        return Collections.singletonList(productInventoryResponse);
      }
    };
  }

}
