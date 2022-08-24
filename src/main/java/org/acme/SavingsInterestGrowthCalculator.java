package org.acme;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/savings-calculator")
public class SavingsInterestGrowthCalculator {

    @POST
    @Operation(summary = "Calculates growth in saving over time given an estimated interest rate")
    @APIResponse(
            responseCode = "200",
            description = "OK, return savings calculation"
    )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SavingsCalculationResponse calculate(SavingsCalculationRequest request)  {

        long totalValue = Math.round(request.savings*Math.pow(1 + request.interestRate/100, request.year));
        long increasedValue = totalValue - request.savings;

        return new SavingsCalculationResponse(request.year,request.savings,totalValue,increasedValue);
//        return new SavingsCalculationResponse(0,0,0,0);
    }

}

class SavingsCalculationResponse {
    int years;
    long savings;
    long totalValue;
    long increasedValue;

    public SavingsCalculationResponse() {
    }

    public SavingsCalculationResponse(int years, long savings, long totalValue, long increasedValue) {
        this.years = years;
        this.savings = savings;
        this.totalValue = totalValue;
        this.increasedValue = increasedValue;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public long getSavings() {
        return savings;
    }

    public void setSavings(long savings) {
        this.savings = savings;
    }

    public long getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(long totalValue) {
        this.totalValue = totalValue;
    }

    public long getIncreasedValue() {
        return increasedValue;
    }

    public void setIncreasedValue(long increasedValue) {
        this.increasedValue = increasedValue;
    }
}

class SavingsCalculationRequest {
    int year;
    double interestRate;
    long savings;

    public SavingsCalculationRequest() {
    }

    public SavingsCalculationRequest(int year, double interestRate, long savings) {
        this.year = year;
        this.interestRate = interestRate;
        this.savings = savings;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public long getSavings() {
        return savings;
    }

    public void setSavings(long savings) {
        this.savings = savings;
    }
}
