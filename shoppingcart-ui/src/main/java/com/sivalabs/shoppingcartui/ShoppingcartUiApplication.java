package com.sivalabs.shoppingcartui;

import com.sivalabs.shoppingcartui.filters.AuthHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
//@EnableOAuth2Sso
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
