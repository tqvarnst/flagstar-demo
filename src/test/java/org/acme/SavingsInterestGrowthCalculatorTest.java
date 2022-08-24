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
          .contentType("application/json")
          .body(new SavingsCalculationRequest(5,2,10000))
          .when().get("/api/savings-calculator")
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