package com.sivalabs.orderservice;

import com.sivalabs.orderservice.entities.Order;
import com.sivalabs.orderservice.util.SampleMockObjectCreator;
import com.sivalabs.orderservice.web.controllers.OrderController;
import com.sivalabs.orderservice.web.service.OrderService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.restdocs.SpringCloudContractRestDocs;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
    "spring.cloud.discovery.enabled = false",
    "spring.cloud.circuit.breaker.enabled= false",
    "spring.cloud.config.enabled= false" })
@ExtendWith(RestDocumentationExtension.class)
public abstract class OrderServiceApplicationTests {

  @Mock
  private OrderService orderService;

  @InjectMocks
  private OrderController orderController;

  @BeforeEach
  public void setup(RestDocumentationContextProvider contextProvider) {
    given(this.orderService.findById(001L))
        .willReturn(SampleMockObjectCreator.getOrder());
    given(this.orderService.save(any(Order.class)))
        .willReturn(SampleMockObjectCreator.getOrder().get());
    RestAssuredMockMvc.mockMvc(MockMvcBuilders.standaloneSetup(orderController)
        .apply(documentationConfiguration(contextProvider))
        .alwaysDo(document("{class-name}/{method-name}", preprocessRequest(),
            preprocessResponse(prettyPrint()), SpringCloudContractRestDocs.dslContract()))
        .build()
    );
  }
}
