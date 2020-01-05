package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description('Find Product By Code')
    request {
        urlPath('/api/products') {
            queryParameters {
                parameter("code", value(consumer(matching("[a-zA-Z0-9]+")), producer('P001')))
            }
        }
        method GET()
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status OK()
        body(
                "id": value(producer(regex("[0-9]+")), consumer("1")),
                "code": fromRequest().query("code"),
                "name": value(producer(regex("[A-Za-z0-9]+"))),
                "description": value(producer(regex("[A-Za-z0-9]+"))),
                "price": value(producer(regex("[0-9].[0-9]+")))
        )
        headers {
            contentType(applicationJson())
        }
    }
}