package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/api/products'
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status OK()
        body([
                [
                        "code"       : "P001",
                        "name"       : "Product 1",
                        "description": "Product 1 description",
                        "price"      : 25
                ],
                [
                        "code"       : "P002",
                        "name"       : "Product 3",
                        "description": "Product 2 description",
                        "price"      : 32
                ],
                [
                        "code"       : "P003",
                        "name"       : "Product 3",
                        "description": "Product 3 description",
                        "price"      : 50
                ]
        ])
        headers {
            contentType(applicationJson())
        }
    }
}