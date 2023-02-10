package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public interface MonetaryTransactions {

    BigDecimal incrementBalance(BigDecimal amount);

    BigDecimal decrementBalance();

    BigDecimal returnChange();


}
