package com.sivalabs.catalogservice.services;

import java.util.List;
import java.util.Optional;

import com.sivalabs.catalogservice.entities.Product;

public interface ProductService {

  List<Product> findAllProducts();

  Optional<Product> findProductByCode(String code);

}
