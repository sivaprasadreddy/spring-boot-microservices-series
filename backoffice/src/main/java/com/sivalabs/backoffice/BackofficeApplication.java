package com.sivalabs.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class BackofficeApplication {

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails resource,
                                                 OAuth2ClientContext context) {
        return new OAuth2RestTemplate(resource, context);
    }
    public static void main(String[] args) {
        SpringApplication.run(BackofficeApplication.class, args);
    }
}
