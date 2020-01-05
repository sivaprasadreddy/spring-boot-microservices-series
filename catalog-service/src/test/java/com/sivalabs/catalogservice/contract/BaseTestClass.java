package com.sivalabs.catalogservice.contract;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
    "spring.cloud.discovery.enabled = false",
    "spring.cloud.config.discovery.enabled = false",
    "spring.cloud.config.enabled = false",
    "spring.sleuth.enabled=false" })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTestClass {

  @Autowired
  private WebApplicationContext context;

  @LocalServerPort
  private int port;

  @BeforeAll
  public void setup() {
    RestAssured.baseURI = "http://localhost:" + this.port;
    RestAssuredMockMvc.standaloneSetup(this.context);
  }

}
