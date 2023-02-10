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

    public void decrementBalance(){

    }

    public BigDecimal dispenseChange(){
        return null;
    }







}
