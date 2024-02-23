/*----------------------------------------------------------------
// AUTHOR: Ellis Totman
// FILENAME: Concessions.java
// SPECIFICATION: This is a concessions stand driver 
// software where users can manually order their items of choice, 
// process an order from a text file, or simply quit the program 
// if not in the mood for ordering desserts.
// FOR: CSE 205 - Assignment #1
// TIME SPENT: 1 day
----------------------------------------------------------------*/
// Class file is inside package "Selgrad".
package Selgrad;

// Imported the ArrayList, List, Scanner, and the concurrent classes from java.util package.
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
// Imported the entire java.io package.
import java.io.*;
// Imported the charset and file classes from java.nio package.
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
// Imported the NumberFormat class from java.text package.
import java.text.NumberFormat;

// Class name, Concessions
public class Concessions 
{
	
	// Private static Scanner set as "answer".
	private static Scanner answer = new Scanner (System.in);
	
	// The main method will throw in IO and interrupted exceptions.
	public static void main(String[] args) throws IOException, InterruptedException	
	{
		
		/* Initialized int variable items, menuChoice, choice, itemCount.
		 * Both the last two variable names are set to 0.
		 * "Total" initialized as a double variable for totaling up 
		 * the prices of the items chosen by the user.
		 * A constant double variable is initialized for the tax rate and
		 * will be added to the grand total.
		 */
		int items, menuChoice, choice = 0, itemCount = 0;
		double total = 0;
		final double tax_rate = 0.05;
		
		/* "InputFile" and "newFile" File classes, standard Scanners "input"
		 *  and "scan, and FileReader class "files" are set to "null" for their
		 *  later usage of creating an Invoice for the user after making
		 *  a purchase. BufferedReader "line" class however, is not set to null.
		 */
		File inputFile = null, newFile = null;
		Scanner input = null, scan = null;
		FileReader files = null;
		BufferedReader line;
		
		// Two string variables are initialized as "userInput" and "option".
		String userInput, option;
		
		// ArrayList created for taking in a number of orders made by the user.
		ArrayList<String> ordered = new ArrayList<String>();
		
		/* 	PrintWriter "invoice" constructor will be used to output the Invoice.txt file.
		 * 	Object "shop" instantiated from "ItemsToSell" constructor.
		 *  NumberFormat set as "dollars" for formatting the sub-total, tax and
		 *  grand total in the invoice receipt.  
		 */
		PrintWriter invoice = new PrintWriter(System.out);
		ItemsToSell shop = new ItemsToSell();
		NumberFormat dollars = NumberFormat.getCurrencyInstance();
		
		/* The while is used to reset back to 
		 * the main menu if the user enters an integer
		 * beyond the range of available options.
		 */
		while (true) 
		{
			// Loading screen 
			System.out.print("Loading Concessions.java");
			/* The program will load "loading" dots
			 * before it sleeps every second.
			 */
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			
			// The PrintWriter constructor is now linked with Invoice.txt.
			invoice = new PrintWriter("Invoice.txt");
			/* Displays main menu consisting of 1) Manual order, 2) Process order
			 * from file and 3) quit.
			 */
			displayMainMenu();
			// Once the user decides, the program will proceed to that option.
			choice = answer.nextInt();
			// The switch statement with three available options.
			switch (choice) 
			{
				
				// Option 1: Manually process an order 
				case 1:
					// Asks the user how many items he/she is willing to purchase.
					System.out.println("How many items are you ordering?");
					items = answer.nextInt();
					/* The integer the user enters will be stored in the 
					 * "items" variable and the number will be the limit of how many items
					 * the user can purchase in the for loop.
					 */
					for (int i = 0; i < items; i++) 
					{
							// Displays menu consisting of eight available options.
							displayMenu();
							// Asks the user to choose an item/
							System.out.println("Please choose item to add");
							menuChoice = answer.nextInt();
							
							/* If the integer matches with the option number,
							 * the itemString will be added to the "ordered"
							 * arrayList, followed by the price added up to the
							 * total.
							 */
							if (menuChoice == 1) 
							{
								ordered.add(shop.giantCookieString());
								total += shop.getGCookiePrice();
							}	
							if (menuChoice == 2) 
							{
								ordered.add(shop.smallCookieString());
								total += shop.getSCookiePrice();
							}
							if (menuChoice == 3) 
							{
								ordered.add(shop.barsString());
								total += shop.getBarsPrice();
							}
							if (menuChoice == 4) 
							{
								ordered.add(shop.cakeString());
								total += shop.getCakePrice();
							}
							if (menuChoice == 5) 
							{
								ordered.add(shop.pieString());
								total += shop.getPiePrice();
							}
							if (menuChoice == 6) 
							{
								ordered.add(shop.brownieString());
								total += shop.getBrowniePrice();
							}
							if (menuChoice == 7) 
							{
								ordered.add(shop.sodaString());
								total += shop.getSodaPrice();
							}
							if (menuChoice == 8) 
							{
								ordered.add(shop.waterString());
								total += shop.getWaterPrice();
							} 
							
							/* If the user enters an invalid integer,
							 * the else if statement will give the user 
							 * a chance to enter a valid option again.
							 * Also, it will not count towards the number
							 * of items.
							 */
							else if (menuChoice < 1 || menuChoice > 8 && menuChoice == i--) 
							{
								System.out.println("Out of Range!");
							}
					}
					
					/* Three double variables "subTotal", "tax" and "grandTotal".
					 * "subTotal" is equal to total and will be displayed on the invoice.
					 * "total" is multiplied by the tax_rate, resulting in the final tax
					 * payment. Finally the grandTotal is added up by the subTotal and tax.
					 */
					double subTotal = total;
					double tax = total * tax_rate;
					double grandTotal = subTotal + tax;
				
					// The name of the concessions stand on top of the receipt.
					invoice.write("\n---------------------------------");
					invoice.write("\nFoodStand  -  Basic Bakers Bakery");
					invoice.write("\n---------------------------------");
					
					// Iterating through a String array.
					for (String receipt: ordered) 
					{
						invoice.write("\n" + receipt);
					}	
					/* "subTotal", "tax" and "grandTotal" are formatted in dollars
					 * and displayed onto the invoice.
					 */
					invoice.write("\n---------------------------------");
					invoice.printf("\n\t\tSubtotal - " + dollars.format(subTotal));
					invoice.printf("\n\t\tTax      - " + dollars.format(tax));
					invoice.printf("\n\t\tTotal    - " + dollars.format(grandTotal));
					invoice.write("\n---------------------------------");
					// Invoice PrintWriter closes.
					invoice.close(); 
					
					// The Invoice.txt is defined in the try block.
					try 
					{
						/* File "inputFile" is linked to Invoice.txt
						 * Scanner "input" is used for the text file.
						 */
						inputFile = new File("Invoice.txt");
						input = new Scanner(inputFile);
					}
					
					// Exception will be thrown if Invoice.txt is not found.
					catch (FileNotFoundException e) 
					{
						e.printStackTrace();
					} 
					// While the Scanner is in place for the file.
					while (input.hasNextLine()) 
					{
						// The Invoice.txt file is displayed onto the console.
						System.out.println(input.nextLine());
					}
					// Input Scanner closes.
					input.close();
					
					/* Asks the user if he/she wishes to purchase more items.
					 * If the user answers "n" (no), the program will quit. 
					 * If answered "y" (yes), the program will go back to the
					 * main menu with the items still inside the shopping cart.
					 */
					System.out.println("\nDo you wish to purchase more items? [y/n]");
					option = answer.next();
					if (option.equals("n")) 
					{
						System.out.println("Come again!");
						System.exit(0);
					} 
					else 
					{
						
					}
					break;
					
				// Option 2: Read an order from a file	
				case 2:
					
					// The loading sequence. 
					System.out.print("Processing order from file");
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.print(".");
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.print(".");
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.print(".");
					TimeUnit.MILLISECONDS.sleep(500);
					
					// Asks user to enter the file name of the order.
					System.out.println("\nPlease enter the filename: ");
					userInput = answer.next();
					/* Tells the user that the program has either found
					 * or not found the text file.
					 */
					System.out.print("Opening " + userInput);
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.print(".");
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.print(".");
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.print(".");
					TimeUnit.MILLISECONDS.sleep(500);
					
					
					try 
					{
						/* FileReader "files" is linked to whatever file
						 * the user has entered via "userInput".
						 */
						files = new FileReader(userInput);
						// Scanner "fileName" set for a file.
						Scanner fileName = new Scanner(new File(userInput));
						System.out.println("\n---------------------");
						System.out.println("Display of your file.");
						
						// Displays a list of items from the text file.
						while (fileName.hasNext()) 
						{
							String order = fileName.nextLine();
							System.out.println(order);
						}
						System.out.println("----------------------------------------");
						
						// BufferedReader "line" used for input of character data from file.
						String s;
						line = new BufferedReader(new FileReader(userInput));
						
						// This while loop will count how many lines (items) the text file holds.
						while ((s = line.readLine()) != null) 
						{
							itemCount++;
						}
						System.out.println("Number of items you ordered: " + itemCount);
						
						// Initialized a List named "cart" with a new ArrayList.
						List<String> cart = new ArrayList<String>();
						
						// Reads all the lines of the text file.
						try 
						{
							cart = Files.readAllLines(Paths.get(userInput), StandardCharsets.UTF_8);
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						
							/* If the text file contains exactly the names of the items
							 * the program will add one of these String methods to the "ordered"
							 * arrayList, followed by one of the getter methods added to the total.
							 */
							if (cart.contains("giantcookie")) 
							{
								ordered.add(shop.giantCookieString());
								total += shop.getGCookiePrice();
							}  
							if (cart.contains("smallcookie")) 
							{
								ordered.add(shop.smallCookieString());
								total += shop.getSCookiePrice();
							} 
							if (cart.contains("bars")) 
							{
								ordered.add(shop.barsString());
								total += shop.getBarsPrice();
							} 
							if (cart.contains("cakesquares")) 
							{
								ordered.add(shop.cakeString());
								total += shop.getCakePrice();
							}
							if (cart.contains("pie")) 
							{
								ordered.add(shop.pieString());
								total += shop.getPiePrice();
							}
							if (cart.contains("jumbobrownie")) 
							{
								ordered.add(shop.brownieString());
								total += shop.getBrowniePrice();
							}
							if (cart.contains("soda")) 
							{
								ordered.add(shop.sodaString());
								total += shop.getSodaPrice();
							}
							if (cart.contains("water")) 
							{
								ordered.add(shop.waterString());
								total += shop.getWaterPrice();
							}
							
						/* Invoice creation. Again, the "total" variable is equal to
						 * "subTotal", "tax" is the product of both "total" and "tax_rate"
						 *  and "grandTotal" is the sum of "subTotal" and "tax".
						 */
						subTotal = total;
						tax = total * tax_rate;
						grandTotal = subTotal + tax;
						invoice.write("\n---------------------------------");
						invoice.write("\nFoodStand  -  Basic Bakers Bakery");
						invoice.write("\n---------------------------------");
						for (String receipt: ordered) 
						{
							invoice.write("\n" + receipt);
						}	
						invoice.write("\n---------------------------------");
						invoice.printf("\n\t\tSubtotal - " + dollars.format(subTotal));
						invoice.printf("\n\t\tTax      - " + dollars.format(tax));
						invoice.printf("\n\t\tTotal    - " + dollars.format(grandTotal));
						invoice.write("\n---------------------------------");
						// Invoice PrintWriter closes.
						invoice.close();
						
						// Displays the Invoice.txt file.
						try 
						{
							newFile = new File("Invoice.txt");
							scan = new Scanner(newFile);
						} 
						catch (FileNotFoundException e) 
						{
							e.printStackTrace();
						} 
						while (scan.hasNextLine()) 
						{
							System.out.println(scan.nextLine());
						}
						// Scanners, Files, FileReaders close.
						files.close();
						line.close();
						fileName.close();
						scan.close();
						
						// Again, asks if the user wishes to purchase more. 
						System.out.println("\nDo you wish to purchase more items? [y/n]");
						option = answer.next();
						if (option.equals("n")) 
						{
							System.out.println("Come again!");
							System.exit(0);
						} 
						else 
						{
							
						}
					} 
					
					// Exception will be thrown if file is not found from the user's input.
					catch (FileNotFoundException e) 
					{
						e.printStackTrace();
						System.out.println("File not Found!");
					}
					break;
				
				// Option 3: Simply quit the program if not feeling like buying anything. 
				case 3:
					System.out.println("Come back if you have second thoughts!");
					System.exit(3);
					break;
				
				// This message will be displayed, if the user enters an invalid option. 
				default:
						System.out.println("Out of Range");
						
				} // End of Switch statement.
			} // End of the while loop.
		} // End of the main method.
	
	/* The displayMainMenu() method will present the
	 * three options before switch statement.
	 */
	private static void displayMainMenu() 
	{
		System.out.println("\nWelcome to Basic Bakers Bakery! \nWould you like to: "
				+ "\n1)Manually process an order \n2)Read an order from a file \n3)Quit");
	}
	
	/* The displayMenu() method will display the 
	 * eight available items for the user to choose.
	 */
	private static void displayMenu() 
	{
		System.out.println("1. Giant Cookie  - $3.00");
		System.out.println("2. Small Cookie  - $1.25");
		System.out.println("3. Bars          - $5.00");
		System.out.println("4. Cake Squares  - $5.50");
		System.out.println("5. Pie           - $6.00");
		System.out.println("6. Jumbo Brownie - $5.50");
		System.out.println("7. Soda          - $1.50");
		System.out.println("8. Bottled Water - $1.25");
	}
} // End of Concessions