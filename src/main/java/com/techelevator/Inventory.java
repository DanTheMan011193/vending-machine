package com.techelevator;

import java.math.BigDecimal;

public class Inventory {

    //attributes
    private String productName;
    private BigDecimal price;
    private String type;
    private int inventoryCount;
    private boolean soldOut;
    private final String SOLD_OUT = "SOLD OUT";

    //constructor
    public Inventory(String productName, String price, String type) {
        this.productName = productName;
        this.price = new BigDecimal(price);
        this.type = type;
        this.inventoryCount = 5;
        this.soldOut = false;
    }

    //getters and setters
    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public String getSOLD_OUT() {
        return SOLD_OUT;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }




}


