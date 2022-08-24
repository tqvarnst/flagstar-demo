package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class SavingsInterestGrowthCalculatorTest {

    @Test
    public void testDefaultEndpoint() {
        given()
          .when().get("/api/savings-calculator?savings=10000&years=5&interestRate=2")
          .then()
                .statusCode(200)
                .body(
                        "$", notNullValue(),
                        "savings",is(10000),
                        "years",is(5),
                        "totalValue", is(11041),
                        "increasedValue",is(1041)
                );

    }

}