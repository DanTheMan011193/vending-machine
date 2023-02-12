package com.techelevator;

import java.math.BigDecimal;

public class PointOfSalesSystem {

    private BigDecimal currentBalance;
    private BigDecimal lastTransaction;
    private BigDecimal salesTotal;


    public PointOfSalesSystem() {
        this.currentBalance = BigDecimal.valueOf(0,2);
        this.lastTransaction = BigDecimal.valueOf(0,2);
        this.salesTotal = BigDecimal.valueOf(0,2);

    }

    //getters and setters
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(BigDecimal lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    public BigDecimal getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(BigDecimal salesTotal) {
        this.salesTotal = salesTotal;
    }

    //methods
    public void incrementBalance(BigDecimal deposit){
            setCurrentBalance(getCurrentBalance().add(deposit));
            setLastTransaction(deposit);
    }

    public void decrementBalance(BigDecimal productCost){
           setCurrentBalance(getCurrentBalance().subtract(productCost));
           setLastTransaction(productCost);
    }

    public String dispenseChange(){
        BigDecimal quarter = BigDecimal.valueOf(0.25);
        BigDecimal dime = BigDecimal.valueOf(0.10);
        BigDecimal nickel = BigDecimal.valueOf(0.05);
        setLastTransaction(getCurrentBalance());
        int[] numberOfCoins = new int[3];

        while (!getCurrentBalance().equals(BigDecimal.ZERO)){
            if (getCurrentBalance().remainder(quarter).equals(BigDecimal.valueOf(0, 2))){
                numberOfCoins[0] = getCurrentBalance().divide(quarter).intValue();
                setCurrentBalance(BigDecimal.ZERO);
            }
            else if (getCurrentBalance().remainder(quarter).remainder(dime).equals(BigDecimal.valueOf(0,2))){
                numberOfCoins[1]++;
                setCurrentBalance(getCurrentBalance().subtract(dime));
            }
            else {
                numberOfCoins[2]++;
                setCurrentBalance(getCurrentBalance().subtract(nickel));
            }
        }
        return String.format("Your change is %d quarters, %d dimes and %d nickels.%n", numberOfCoins[0], numberOfCoins[1], numberOfCoins[2]);
    }










}
