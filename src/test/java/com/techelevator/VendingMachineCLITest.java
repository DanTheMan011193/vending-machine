package com.techelevator;

import com.techelevator.view.VendingMenu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineCLITest {

    private Map<String, Inventory> testMap = new HashMap<>();
    private VendingMenu testMenu;
    private VendingMachineCLI test;

    @Before
    public void createVendingMachineCLI() {
        this.test = new VendingMachineCLI(testMenu);
    }

    @Test
    public void stockInventory_populates_map_from_csv_file_selection_should_return_true() {
        //Arrange
        test.stockInventory(testMap, "vendingmachine.csv");
        String expectedResultKeyA2Type = "Chip";

        //Act
        String actualResultKeyA2Type = testMap.get("A2").getType();

        //Assert
        Assert.assertEquals(expectedResultKeyA2Type, actualResultKeyA2Type);
    }

    @Test
    public void decrementInventory_decreases_selected_inventory_one_should_return_true() {
        //Arrange
        test.stockInventory(testMap, "vendingmachine.csv");
        int expectedResultKeyA2Count = 4;
        test.decrementInventory(testMap, "A2");

        //Act
        int actualResultKeyA2Count = testMap.get("A2").getInventoryCount();

        //Assert
        Assert.assertEquals(expectedResultKeyA2Count, actualResultKeyA2Count);
    }

    @Test
    public void dispenseItem_expected_array_for_selected_inventory_should_return_true() {
        //Arrange
        test.stockInventory(testMap, "vendingmachine.csv");
        String[] expectedResultKeyA2 = {"Stackers", "1.45", "0.00", "Crunch Crunch, It's Yummy!"};

        //Act
        String[] actualResultKeyA2 = test.dispenseItem(testMap, "A2");

        //Assert
        Assert.assertEquals(expectedResultKeyA2, actualResultKeyA2);
    }





}
