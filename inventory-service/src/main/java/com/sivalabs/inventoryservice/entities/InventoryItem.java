package com.sivalabs.inventoryservice.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "inventory")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;
    @Column(name = "quantity")
    private Integer availableQuantity = 0;
}
