# spring-boot-microservices-series
Code for [SpringBoot MicroServices Blog Series](https://sivalabs.in/2018/03/microservices-using-springboot-spring-cloud-part-1-overview/)

## How to run?

### Build all modules:

`spring-boot-microservices-series> ./mvnw clean package -DskipTests=true`

### Start infrastructure modules in docker:

**The simplest way to run all the services in Docker:**

`spring-boot-microservices-series> ./run.sh start_all`

**To start only infrastructure services (mysqldb, rabbitmq, config-server, service-registry, hystrix-dashboard) in docker:**

`spring-boot-microservices-series> ./run.sh start_infra`

**Start each microservice either in local or in docker:**

**Local:** `spring-boot-microservices-series/catalog-service> ./mvnw spring-boot:run`

**Docker:** `spring-boot-microservices-series> ./run.sh start <service>`

Ex: `spring-boot-microservices-series> ./run.sh start catalog-service`


* MySQL container:
     * hostname: mysqldb
     * Ports : 3306:3306 (<host_port>:<container_port>)
     * Username/Password: root/admin

* RabbitMQ:
     * hostname: rabbitmq
     * Ports: 5672:5672, 15672:15672
     * Admin UI: http://localhost:15672
     * Username/password: guest/guest

* Vault:
    * hostname: vault
    * Ports: 8200:8200
    * Root token: 934f9eae-31ff-a8ef-e1ca-4bea9e07aa09

* config-server:
    * hostname: config-server
    * Ports: 8888:8888
    * URL: http://localhost:8888/

* service-registry:
    * hostname: service-registry
    * Ports: 8761:8761
    * URL: http://localhost:8761/
    
* hystrix-dashboard:
    * hostname: hystrix-dashboard
    * Ports: 8788:8788
    * URL: http://localhost:8788/hystrix

* catalog-service:
    * hostname: catalog-service
    * Ports: 18181:8181
    * URL: http://localhost:18181
    
* inventory-service   
    * hostname: inventory-service
    * Ports: 18282:8282
    * URL: http://localhost:18282
    
* order-service  
    * hostname: order-service
    * Ports: 18383:8383
    * URL: http://localhost:18383 
    
* shoppingcart-ui    
    * hostname: shoppingcart-ui
    * Ports: 18080:8080
    * URL: http://localhost:18080