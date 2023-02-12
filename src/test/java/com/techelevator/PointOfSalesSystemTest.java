package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class PointOfSalesSystemTest {

    private PointOfSalesSystem test;

    @Before
    public void createPointOfSalesSystem() {
        this.test = new PointOfSalesSystem();
    }

    @Test
    public void contructor_attributes_with_zero_balances_should_return_true() {
        //Arrange
        BigDecimal expectedResult = BigDecimal.valueOf(0, 2);

        //Act
        BigDecimal actualResult = test.getCurrentBalance().add(test.getLastTransaction().add(test.getSalesTotal()));

        //Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void incrementBalance_when_given_100_increases_currentBalance_lastTransaction_should_return_true() {
        //Arrange
        BigDecimal expectedResult = BigDecimal.valueOf(100, 2);

        //Act
        test.incrementBalance(BigDecimal.valueOf(100, 2));
        BigDecimal actualResultCurrentBalance = test.getCurrentBalance();
        BigDecimal actualResultLastTransaction = test.getLastTransaction();

        //Assert
        Assert.assertEquals(expectedResult, actualResultCurrentBalance);
        Assert.assertEquals(expectedResult, actualResultLastTransaction);
    }

    @Test
    public void incrementBalance_when_given_two_transactions_currentBalanceequalsboth_lastTransactionequalslast_should_return_true() {
        //Arrange
        BigDecimal expectedResultCurrentBalance = BigDecimal.valueOf(200, 2);
        BigDecimal expectedResultLastTransaction = BigDecimal.valueOf(100, 2);

        //Act
        test.incrementBalance(BigDecimal.valueOf(100, 2));
        test.incrementBalance(BigDecimal.valueOf(100, 2));
        BigDecimal actualResultCurrentBalance = test.getCurrentBalance();
        BigDecimal actualResultLastTransaction = test.getLastTransaction();

        //Assert
        Assert.assertEquals(expectedResultCurrentBalance, actualResultCurrentBalance);
        Assert.assertEquals(expectedResultLastTransaction, actualResultLastTransaction);
    }

    @Test
    public void decrementBalance_when_given_100_decreases_currentBalance_sets_lastTransaction_100_should_return_true() {
        //Arrange
        BigDecimal expectedResultCurrentBalance = BigDecimal.valueOf(-100, 2);
        BigDecimal expectedResultLastTransaction = BigDecimal.valueOf(100, 2);

        //Act
        test.decrementBalance(BigDecimal.valueOf(100, 2));
        BigDecimal actualResultCurrentBalance = test.getCurrentBalance();
        BigDecimal actualResultLastTransaction = test.getLastTransaction();

        //Assert
        Assert.assertEquals(expectedResultCurrentBalance, actualResultCurrentBalance);
        Assert.assertEquals(expectedResultLastTransaction, actualResultLastTransaction);
    }

    @Test
    public void decrementBalance_when_given_two_transactions_currentBalanceEqualsBoth_lastTransactionEqualsLast_should_return_true() {
        //Arrange
        BigDecimal expectedResultCurrentBalance = BigDecimal.valueOf(-200, 2);
        BigDecimal expectedResultLastTransaction = BigDecimal.valueOf(100, 2);

        //Act
        test.decrementBalance(BigDecimal.valueOf(100, 2));
        test.decrementBalance(BigDecimal.valueOf(100, 2));
        BigDecimal actualResultCurrentBalance = test.getCurrentBalance();
        BigDecimal actualResultLastTransaction = test.getLastTransaction();

        //Assert
        Assert.assertEquals(expectedResultCurrentBalance, actualResultCurrentBalance);
        Assert.assertEquals(expectedResultLastTransaction, actualResultLastTransaction);
    }

    @Test
    public void dispenseChange_when_given_twoDollars_returns_eightQuarters_currentBalanceEqualsZero_lastTransactionEqualsTwo_should_be_true() {
        //Arrange
        String expectedResult = String.format("Your change is %d quarters, %d dimes and %d nickels.%n", 8, 0, 0);
        BigDecimal expectedResultCurrentBalance = BigDecimal.valueOf(0, 0);
        BigDecimal expectedResultLastTransaction = new BigDecimal("2.00");
        test.incrementBalance(BigDecimal.valueOf(2, 0));

        //Act
        String actualResult = test.dispenseChange();
        BigDecimal actualResultCurrentBalance = test.getCurrentBalance();
        BigDecimal actualResultLastTransaction = test.getLastTransaction();

        //Assert
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedResultCurrentBalance, actualResultCurrentBalance);
        Assert.assertEquals(expectedResultLastTransaction, actualResultLastTransaction);
    }

    @Test
    public void dispenseChange_when_given_twoDollarsFortyCents_returns_nineQuartersOneDimeOneNickel_currentBalanceEqualsZero_lastTransactionEqualsTwo_should_be_true() {
        //Arrange
        String expectedResult = String.format("Your change is %d quarters, %d dimes and %d nickels.%n", 9, 1, 1);
        BigDecimal expectedResultCurrentBalance = BigDecimal.valueOf(0, 0);
        BigDecimal expectedResultLastTransaction = new BigDecimal("2.40");
        test.incrementBalance(new BigDecimal("2.40"));

        //Act
        String actualResult = test.dispenseChange();
        BigDecimal actualResultCurrentBalance = test.getCurrentBalance();
        BigDecimal actualResultLastTransaction = test.getLastTransaction();

        //Assert
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedResultCurrentBalance, actualResultCurrentBalance);
        Assert.assertEquals(expectedResultLastTransaction, actualResultLastTransaction);
    }
}
