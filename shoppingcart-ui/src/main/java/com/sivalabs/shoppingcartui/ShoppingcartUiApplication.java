package com.sivalabs.shoppingcartui;

import com.sivalabs.shoppingcartui.filters.AuthHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class ShoppingcartUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartUiApplication.class, args);
    }

    @Bean
    AuthHeaderFilter authHeaderFilter() {
        return new AuthHeaderFilter();
    }
}
