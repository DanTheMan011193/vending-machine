package com.techelevator;


import java.math.BigDecimal;

public class VendingMachine extends PointOfSalesSystem {

    //attributes
    private String slotLocation;
    private Inventory products;

    //constructor
    public VendingMachine() {

    }

    public void incrementBalance(BigDecimal deposit) {
        super.incrementBalance(deposit);
    }


public String dispenseChange(){
       return super.dispenseChange();

}


}
