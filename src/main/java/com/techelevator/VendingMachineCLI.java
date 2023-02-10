package com.techelevator;

import com.techelevator.view.VendingMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class VendingMachineCLI implements MonetaryTransactions {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};
	private static final Scanner userInput = new Scanner(System.in);


	private VendingMenu menu;

	public VendingMachineCLI(VendingMenu menu) {
		this.menu = menu;
	}
	BigDecimal currentBalance = BigDecimal.valueOf(0.00);
	public void run() {
		boolean running = true;
		Map<String, Inventory> inventoryStock = new HashMap<>();
		boolean isInventoryStocked = false;

		while (running) {

			if (!isInventoryStocked) {
				stockInventory(inventoryStock, "vendingmachine.csv");
				isInventoryStocked = true;
			}

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			// A switch statement could also be used here.  Your choice.
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				for (Map.Entry<String, Inventory> item : inventoryStock.entrySet()) {
					if (item.getValue().getInventoryCount() == 0) {
						System.out.println(item.getKey() + item.getValue().getProductName() + item.getValue().getPrice() + item.getValue().getSOLD_OUT());
					} else {
						System.out.println(item.getKey() + item.getValue().getProductName() + item.getValue().getPrice() + item.getValue().getInventoryCount());
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				BigDecimal currentBalance = BigDecimal.valueOf(0.00);
				// do purchase
				String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				BigDecimal userDeposit = null;
				try (userInput) {
					userDeposit = new BigDecimal(userInput.nextLine());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				incrementBalance(userDeposit);
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				running = false;
			}
		}

	}

	public static void main(String[] args) {
		VendingMenu menu = new VendingMenu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	/*
	step 1 - open the scanner
	step 2 - have the scanner read the csv file
	step 3 - use the split method to separate the line into individual values

	step 4 - assign the individual values the first value as the map string and their remaining
	values as the inventory attributes


	 */

	private static Map<String, Inventory> stockInventory(Map<String, Inventory> stock, String inventorySourcePath) {
		File inventoryFile = new File(inventorySourcePath);

		try (Scanner fileInput = new Scanner(inventoryFile)){

			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] inventoryData = line.split("\\|");
				stock.put(inventoryData[0],
						new Inventory(inventoryData[1], inventoryData[2], inventoryData[3]));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return stock;
	}



	@Override
	public BigDecimal incrementBalance(BigDecimal amount) {

			currentBalance.add(amount);

		return currentBalance;
	}

	@Override
	public BigDecimal decrementBalance() {
		return null;
	}

	@Override
	public BigDecimal returnChange() {
		return null;
	}
}
