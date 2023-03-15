/*----------------------------------------------------------------------
// AUTHOR: Ellis Totman
// FILENAME: ItemsToSell.java
// SPECIFICATION: This class contains the names of the items and their
// prices. Along with getter and string methods for the Concessions.java
// driver file to extract from. 
// FOR: CSE 205 - Assignment #1
// TIME SPENT: 15 minutes
----------------------------------------------------------------------*/
// Class file is inside package "Selgrad".
package Selgrad;

// Imported the NumberFormat class from java.text package.
import java.text.NumberFormat;

// Class name, ItemsToSell
public class ItemsToSell 
{
	/* The name of the items, initialized as strings variables, 
	 * and their prices initialized as double variables.
	 */
	private String itemOne, itemTwo, itemThree, itemFour, itemFive, itemSix, itemSeven, itemEight;
	private double priceOne, priceTwo, priceThree, priceFour, priceFive, priceSix, priceSeven, priceEight;
	
	// NumberFormat set as "dollars" for formatting the item prices. 
	NumberFormat dollars = NumberFormat.getCurrencyInstance();
	
	/* Instantiation of this constructor will bring
	 * the item names, along with the prices in 
	 * order.
	 */
	public ItemsToSell( ) 
	{
		itemOne = "GIANTCOOKIE";
		itemTwo = "SMALLCOOKIE";
		itemThree = "BARS";
		itemFour = "CAKESQUARES";
		itemFive = "PIE";
		itemSix = "JUMBOBROWNIE";
		itemSeven = "SODA";
		itemEight = "WATER";
		priceOne = 3.00;
		priceTwo = 1.25;
		priceThree = 5.00;
		priceFour = 5.50;
		priceFive = 6.00;
		priceSix = 5.50;
		priceSeven = 1.50;
		priceEight = 1.25;
	}
	
	/* This set of eight getter
	 * methods below will return 
	 * the prices of the items
	 * once the user makes a choice.
	 */
	public double getGCookiePrice( ) 
	{
		return priceOne;
	}
	
	public double getSCookiePrice( ) 
	{
		return priceTwo;
	}
	
	public double getBarsPrice( ) 
	{
		return priceThree;
	}
	
	public double getCakePrice( ) 
	{
		return priceFour;
	}
	
	public double getPiePrice( ) 
	{
		return priceFive;
	}
	
	public double getBrowniePrice( ) 
	{
		return priceSix;
	}
	
	public double getSodaPrice( ) 
	{
		return priceSeven;
	}
	
	public double getWaterPrice( ) 
	{
		return priceEight;
	}
	
	/* Another set of eight methods below, but these will return the 
	 * item name and price, and it will be stored into 
	 * an arrayList, again, once the user makes a choice.
	 * Also, the currency formatting is being done on the prices.
	 */
	public String giantCookieString( ) 
	{
		return (itemOne + "     -           " 
				+ dollars.format(priceOne));
	}
	
	public String smallCookieString( ) 
	{
		return (itemTwo + "     -           " 
				+ dollars.format(priceTwo));
	}
	
	public String barsString( ) 
	{
		return (itemThree + "            -           "
				+ dollars.format(priceThree));
	}
	
	public String cakeString( ) 
	{
		return (itemFour + "     -           "
				+ "" + dollars.format(priceFour));
	}
	
	public String pieString( ) 
	{
		return (itemFive + "             -           "
				+ dollars.format(priceFive)); 
	}
	
	public String brownieString( ) 
	{
		return (itemSix + "    -           " 
				+ dollars.format(priceSix));
	}
	
	public String sodaString( ) 
	{
		return (itemSeven + "            -           " 
				+ dollars.format(priceSeven));
	}
	
	public String waterString( ) 
	{
		return (itemEight + "           -           " 
				+ dollars.format(priceEight));
	}
} // End of class