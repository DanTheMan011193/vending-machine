package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {

    private Inventory test;

    @Before
    public void createInventory() {
        this.test = new Inventory("testName", "99.99", "testType");
    }

    @Test
    public void class_constructor_test_fields_should_return_true() {
        //Arrange
        String[] expectedResult = {"testName", "99.99", "testType", "5", "false"};

        //Act
        String[] actualResult = new String[5];
        actualResult[0] = test.getProductName();
        actualResult[1] = String.valueOf(test.getPrice());
        actualResult[2] = test.getType();
        actualResult[3] = String.valueOf(test.getInventoryCount());
        actualResult[4] = String.valueOf(test.isSoldOut());

        //Assert
        Assert.assertArrayEquals(expectedResult, actualResult);
    }
}
