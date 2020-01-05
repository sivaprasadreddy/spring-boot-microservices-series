package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.verifier.util.ContractVerifierUtil

Contract.make {
    description('Find Product By Code')
    request {
        urlPath('/api/products') {
            queryParameters {
                parameter 'code': value(consumer(matching("[a-zA-Z0-9]+")), producer('P001'))
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
                [
                        "code"       : "P001",
                        "name"       : "Product 1",
                        "description": "Product 1 description",
                        "price"      : 25
                ]
        )
        headers {
            contentType(applicationJson())
        }
    }
}