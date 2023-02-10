package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public interface MonetaryTransactions {

    void incrementBalance();

    BigDecimal decrementBalance();

    BigDecimal returnChange();


}
