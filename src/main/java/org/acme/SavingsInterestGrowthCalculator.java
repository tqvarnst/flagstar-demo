package org.acme;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/api/savings-calculator")
public class SavingsInterestGrowthCalculator {

    @GET
    @Operation(summary = "Calculates growth in saving over time given an estimated interest rate")
    @APIResponse(
            responseCode = "200",
            description = "OK, return savings calculation"
    )
    public SavingCalculationResponse calculate(@QueryParam("savings") @Parameter(description = "Total savings to start with") long savings,
                            @QueryParam("interestRate") double interestRate,
                            @QueryParam("years") @Parameter(description = "Number of years") int years) {

        long totalValue = Math.round(savings*Math.pow(1 + interestRate/100,years));
        long increasedValue = totalValue - savings;

        return new SavingCalculationResponse(years,savings,totalValue,increasedValue);

    }

}

class SavingCalculationResponse {
    public int years;
    public long savings;
    public long totalValue;
    public long increasedValue;

    public SavingCalculationResponse(int years, long savings, long totalValue, long increasedValue) {
        this.years = years;
        this.savings = savings;
        this.totalValue = totalValue;
        this.increasedValue = increasedValue;
    }
}
