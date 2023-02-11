package com.techelevator.view;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class VendingMenu {

	private static final File LOG_FILE = new File("log.txt");
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
	private PrintWriter out;
	private Scanner in;
	private String balance;

	public VendingMenu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	public BigDecimal getDepositAmountFromUserInput(Scanner input) {
		System.out.println("Feed money in whole dollar amounts");

		BigDecimal userInput = input.nextBigDecimal();
		return userInput;
	}

	public String getProductSelectionFromUserInput(Scanner input) {
		String productSlot = input.nextLine();
		return productSlot;
	}

	public void logPurchaseTransaction(String item, String itemCode, BigDecimal amount, BigDecimal balance) {
		// Perform the transaction


		try (FileWriter writer = new FileWriter(LOG_FILE, true)) {

			writer.write(DATE_FORMAT.format(new Date()) + " " + item + " " + itemCode + " $" + amount + " $" + balance + "\n");

		} catch (IOException e) {
			System.err.println("Error writing to log file: " + e.getMessage());
		}
	}

	public void logDepositTransaction(BigDecimal amount, BigDecimal balance) {
		// Perform the transaction


		try (FileWriter writer = new FileWriter(LOG_FILE, true)) {

			writer.write(DATE_FORMAT.format(new Date()) + " " + "FEED MONEY: $" + amount + " $" + balance + "\n");

		} catch (IOException e) {
			System.err.println("Error writing to log file: " + e.getMessage());
		}
	}

	public void logCashOutTransaction(BigDecimal amount, BigDecimal balance) {
		// Perform the transaction


		try (FileWriter writer = new FileWriter(LOG_FILE, true)) {

			writer.write(DATE_FORMAT.format(new Date()) + " " + "GIVE CHANGE: $" + amount + " $" + balance + "\n");

		} catch (IOException e) {
			System.err.println("Error writing to log file: " + e.getMessage());
		}
	}

}
