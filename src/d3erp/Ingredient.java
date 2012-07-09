package d3erp;

import java.util.ArrayList;

/**
 *
 * @author Neithan
 */
public class Ingredient
{
	private int id;
	private String name;
	private ArrayList<Stock> inventory;

	/**
	 * @param id
	 * @param name
	 */
	public Ingredient(int id, String name)
	{
		this.id = id;
		this.name = name;
		this.inventory = new ArrayList<>();
	}

	/**
	 * set the name of this ingredient
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * get the ingredient id
	 * @return int
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 * get an ArrayList of the stock entries
	 * @return ArrayList
	 */
	public ArrayList<Stock> getInventory()
	{
		return inventory;
	}

	/**
	 * get the name of this ingredient
	 * @return String
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * add a stock entry
	 * @param stock
	 */
	public void addStock(Stock stock)
	{
		this.inventory.add(stock);
	}

	/**
	 * add a stock entry via amount and price
	 * @param amount
	 * @param price
	 */
	public void addStock(int amount, double price)
	{
		this.addStock(new Stock(amount, price));
	}

	/**
	 * get the stock entry with the key index
	 * @param index
	 * @return Stock
	 */
	public Stock getStock(int index)
	{
		return this.inventory.get(index);
	}

	/**
	 * get the total usable amount
	 * @return int
	 */
	public int getTotalAmount()
	{
		int amount = 0;

		for (int i = 0; i < this.inventory.size(); i++)
			amount += this.inventory.get(i).getUsableAmount();

		return amount;
	}
}