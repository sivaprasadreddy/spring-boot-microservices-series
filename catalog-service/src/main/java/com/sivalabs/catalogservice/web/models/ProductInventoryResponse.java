package com.sivalabs.catalogservice.web.models;

import lombok.Data;

@Data
public class ProductInventoryResponse {
    private String productCode;
    private Integer availableQuantity = 0;
}
