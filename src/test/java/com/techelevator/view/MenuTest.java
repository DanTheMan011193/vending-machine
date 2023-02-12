package com.techelevator.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.techelevator.view.VendingMenu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MenuTest {

	private ByteArrayOutputStream output;
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

	@Before
	public void setup() {
		output = new ByteArrayOutputStream();
	}

	@Test
	public void displays_a_list_of_menu_options_and_prompts_user_to_make_a_choice() {
		Object[] options = new Object[] { Integer.valueOf(3), "Blind", "Mice" };
		VendingMenu menu = getMenuForTesting();

		menu.getChoiceFromOptions(options);

		String expected = System.lineSeparator() + "1) " + options[0].toString() + System.lineSeparator() + "2) " + options[1].toString() + System.lineSeparator() + "3) "
				+ options[2].toString() + System.lineSeparator() + System.lineSeparator() + "Please choose an option >>> ";
		Assert.assertEquals(expected, output.toString());
	}

	@Test
	public void returns_object_corresponding_to_user_choice() {
		Integer expected = Integer.valueOf(456);
		Integer[] options = new Integer[] { Integer.valueOf(123), expected, Integer.valueOf(789) };
		VendingMenu menu = getMenuForTestingWithUserInput("2" + System.lineSeparator());

		Integer result = (Integer) menu.getChoiceFromOptions(options);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void redisplays_menu_if_user_does_not_choose_valid_option() {
		Object[] options = new Object[] { "Larry", "Curly", "Moe" };
		VendingMenu menu = getMenuForTestingWithUserInput("4" + System.lineSeparator() + "1" + System.lineSeparator());

		menu.getChoiceFromOptions(options);

		String menuDisplay = System.lineSeparator() + "1) " + options[0].toString() + System.lineSeparator() + "2) " + options[1].toString() + System.lineSeparator() + "3) "
				+ options[2].toString() + System.lineSeparator() + System.lineSeparator() + "Please choose an option >>> ";

		String expected = menuDisplay + System.lineSeparator() + "*** 4 is not a valid option ***" + System.lineSeparator() + System.lineSeparator() + menuDisplay;

		Assert.assertEquals(expected, output.toString());
	}

	@Test
	public void redisplays_menu_if_user_chooses_option_less_than_1() {
		Object[] options = new Object[] { "Larry", "Curly", "Moe" };
		VendingMenu menu = getMenuForTestingWithUserInput("0" + System.lineSeparator() + "1" + System.lineSeparator());

		menu.getChoiceFromOptions(options);

		String menuDisplay = System.lineSeparator() + "1) " + options[0].toString() + System.lineSeparator() + "2) " + options[1].toString() + System.lineSeparator() + "3) "
				+ options[2].toString() + System.lineSeparator() + System.lineSeparator() + "Please choose an option >>> ";

		String expected = menuDisplay + System.lineSeparator() + "*** 0 is not a valid option ***" + System.lineSeparator() + System.lineSeparator() + menuDisplay;

		Assert.assertEquals(expected, output.toString());
	}

	@Test
	public void redisplays_menu_if_user_enters_garbage() {
		Object[] options = new Object[] { "Larry", "Curly", "Moe" };
		VendingMenu menu = getMenuForTestingWithUserInput("Mickey Mouse" + System.lineSeparator() + "1" + System.lineSeparator());

		menu.getChoiceFromOptions(options);

		String menuDisplay = System.lineSeparator() + "1) " + options[0].toString() + System.lineSeparator() + "2) " + options[1].toString() + System.lineSeparator() + "3) "
				+ options[2].toString() + System.lineSeparator() + System.lineSeparator() + "Please choose an option >>> ";

		String expected = menuDisplay + System.lineSeparator() + "*** Mickey Mouse is not a valid option ***" + System.lineSeparator() + System.lineSeparator() + menuDisplay;

		Assert.assertEquals(expected, output.toString());
	}

	@Test
	public void returns_BigDecimal_depositAmount_corresponding_to_userInput() {
		//Arrange
		BigDecimal expectedResult = new BigDecimal("100");
		VendingMenu menu = getMenuForTestingWithUserInput("100");

		//Act
		BigDecimal actualResult = menu.getDepositAmount();

		//Assert
		Assert.assertEquals(expectedResult,actualResult);
	}

	@Test
	public void returns_String_productSelection_corresponding_to_userInput() {
		//Arrange
		String expectedResult = "A2";
		VendingMenu menu = getMenuForTestingWithUserInput("A2");

		//Act
		String actualResult = menu.getProductSelection();

		//Assert
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void returns_expectedResult_format_corresponding_to_receipt_fields_entered() {
		//Arrange
		String[] test = {"testName", "0.00", "0.00", "test"};
		VendingMenu testMenu = getMenuForTesting();
		String expectedResult = System.lineSeparator() + String.format("Product: %s%n" +
						"Price: $%s%n" +
						"Balance remaining: $%s%n" +
						"%s%n",
				test[0], test[1], test[2], test[3]);

		//Act
		testMenu.getReceipt(test);

		//Assert
		Assert.assertEquals(expectedResult, output.toString());
	}

	@Test
	public void returns_expectedResult_purchaseInformation_corresponding_to_log_info_entered() throws IOException {
		//Arrange
		VendingMenu testMenu = getMenuForTesting();
		String expectedResult = Files.readString(Path.of("log.txt")) + DATE_FORMAT.format(new Date()) + " " + "test" + " " + "itemCode" + " $" + "1.00" + " $" + "1.00" + "\n";

		//Act
		testMenu.logPurchaseTransaction("test", "itemCode", new BigDecimal("1.00"), new BigDecimal("1.00"));

		//Assert
		Assert.assertTrue(Files.exists(Path.of("log.txt")));
		String actualResult = Files.readString(Path.of("log.txt"));
		Assert.assertEquals(expectedResult.trim(), actualResult.trim());

	}

	@Test
	public void returns_expectedResult_depositInformation_corresponding_to_log_info_entered() throws IOException {
		//Arrange
		VendingMenu testMenu = getMenuForTesting();
		String expectedResult = Files.readString(Path.of("log.txt")) + DATE_FORMAT.format(new Date()) + " " + "FEED MONEY: $" + "1.00" + " $" + "1.00" + "\n";

		//Act
		testMenu.logDepositTransaction(new BigDecimal("1.00"), new BigDecimal("1.00"));

		//Assert
		Assert.assertTrue(Files.exists(Path.of("log.txt")));
		String actualResult = Files.readString(Path.of("log.txt"));
		Assert.assertEquals(expectedResult.trim(), actualResult.trim());
	}

	@Test
	public void returns_expectedResult_cashOutInformation_corresponding_to_log_info_entered() throws IOException {
		//Arrange
		VendingMenu testMenu = getMenuForTesting();
		String expectedResult = Files.readString(Path.of("log.txt")) + DATE_FORMAT.format(new Date()) + " " + "GIVE CHANGE: $" + "1.00" + " $" + "1.00" + "\n";

		//Act
		testMenu.logCashOutTransaction(new BigDecimal("1.00"), new BigDecimal("1.00"));

		//Assert
		Assert.assertTrue(Files.exists(Path.of("log.txt")));
		String actualResult = Files.readString(Path.of("log.txt"));
		Assert.assertEquals(expectedResult.trim(), actualResult.trim());
	}

	private VendingMenu getMenuForTestingWithUserInput(String userInput) {
		ByteArrayInputStream input = new ByteArrayInputStream(String.valueOf(userInput).getBytes());
		return new VendingMenu(input, output);
	}

	private VendingMenu getMenuForTesting() {
		return getMenuForTestingWithUserInput("1" + System.lineSeparator());
	}
}
