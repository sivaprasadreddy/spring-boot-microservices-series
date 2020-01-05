package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description('Create Order')
    request {
        method POST()
        headers {
            contentType(applicationJson())
        }
        body(file("request.json"))
        url("/api/orders")
    }
    response {
        status OK()
        body(file("response.json"))
        headers {
            contentType(applicationJson())
        }
        bodyMatchers {
            // asserts the jsonpath value against manual regex
            jsonPath('$.id', byRegex(positiveInt()).asLong())
            jsonPath('$.customerEmail', byRegex(email()).asString())
        }
    }
}