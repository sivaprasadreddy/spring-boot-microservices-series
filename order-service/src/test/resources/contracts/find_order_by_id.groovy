package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description('Find Order By Id')
    request {
        url('/api/orders/1')

        method GET()
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status OK()
        body(
                "id": value(producer(regex(positiveInt())), consumer(1)),
                "customerEmail": value(producer(regex(email())), consumer("dummyEmail@json.com")),
                "customerAddress": value(producer(regex(alphaNumeric())), consumer('dummyAddress')),
                "items": [([
                        "productId"   : value(producer(regex(positiveInt())), consumer(1)),
                        "quantity"    : value(producer(regex(positiveInt())), consumer(20)),
                        "productPrice": value(producer(regex(aDouble())), consumer(25.95))
                ])]
        )
        headers {
            contentType(applicationJson())
        }
    }
}