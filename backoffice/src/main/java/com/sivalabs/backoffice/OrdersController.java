package com.sivalabs.backoffice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class OrdersController {

    @GetMapping("/api/orders")
    public List<String> orders() {
        return Arrays.asList("Order-1","Order-2");
    }
}
