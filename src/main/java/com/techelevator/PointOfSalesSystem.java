package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public abstract class PointOfSalesSystem {

    private BigDecimal currentBalance;
    private BigDecimal feedMoney;
    private BigDecimal salesTotal;


    public PointOfSalesSystem() {
        this.currentBalance = BigDecimal.valueOf(0,2);
        this.feedMoney = BigDecimal.valueOf(0,2);
        this.salesTotal = BigDecimal.valueOf(0,2);

    }
        //getters and setters
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getFeedMoney() {
        return feedMoney;
    }

    public void setFeedMoney(BigDecimal feedMoney) {
        this.feedMoney = feedMoney;
    }

    public BigDecimal getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(BigDecimal salesTotal) {
        this.salesTotal = salesTotal;
    }

    //methods

    public void incrementBalance(BigDecimal deposit){
            /*
		When the user enters a value, that value needs to increment a balance variable holding the users balance
			Step 1: Prompt user to feed money in whole dollar amounts
			Step 2: Allow user to enter amount
			Step 3: increment user's current balance variable by amount stated in step 2 above.
		 */


            setCurrentBalance(getCurrentBalance().add(deposit));
            setFeedMoney(deposit);



    }

    public void decrementBalance(BigDecimal productCost){
           setCurrentBalance(getCurrentBalance().subtract(productCost));
           setFeedMoney(productCost);
    }

    public String dispenseChange(){
        BigDecimal quarter = BigDecimal.valueOf(0.25);
        BigDecimal dime = BigDecimal.valueOf(0.10);
        BigDecimal nickel = BigDecimal.valueOf(0.05);
        setFeedMoney(getFeedMoney().add(getCurrentBalance()));
        int[] numberOfCoins = new int[3];

        while (!getCurrentBalance().equals(BigDecimal.ZERO)){
            if (getCurrentBalance().remainder(quarter).equals(BigDecimal.ZERO)){
                numberOfCoins[0] = getCurrentBalance().divide(quarter).intValue();
                setCurrentBalance(BigDecimal.ZERO);
            }
            else if (getCurrentBalance().remainder(dime).equals(BigDecimal.ZERO)){
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
