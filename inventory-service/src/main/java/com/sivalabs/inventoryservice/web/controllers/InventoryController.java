package com.sivalabs.inventoryservice.web.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sivalabs.inventoryservice.entities.InventoryItem;
import com.sivalabs.inventoryservice.repositories.InventoryItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class InventoryController {
    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryController(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @GetMapping("/api/inventory/{productCode}")
    @HystrixCommand
    public ResponseEntity<InventoryItem> findInventoryByProductCode(@PathVariable("productCode") String productCode) {
        log.info("Finding inventory for product code :"+productCode);
        Optional<InventoryItem> inventoryItem = inventoryItemRepository.findByProductCode(productCode);
        if(inventoryItem.isPresent()) {
            return new ResponseEntity(inventoryItem, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/inventory")
    @HystrixCommand
    public List<InventoryItem> getInventory() {
        log.info("Finding inventory for all products ");
        return inventoryItemRepository.findAll();
    }
}
