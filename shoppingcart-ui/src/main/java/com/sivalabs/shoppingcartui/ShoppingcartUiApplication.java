package com.sivalabs.shoppingcartui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ShoppingcartUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartUiApplication.class, args);
    }
}
