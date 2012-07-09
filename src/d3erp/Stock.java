package d3erp;

import java.util.ArrayList;

/**
 *
 * @author Neithan
 */
public class Stock
{
	private int amount;
	private double price;
	private ArrayList<Integer> usedAmounts;

	/**
	 * @param amount
	 * @param price
	 */
	public Stock(int amount, double price)
	{
		this.amount = amount;
		this.price = price;
		this.usedAmounts = new ArrayList<>();
	}

	/**
	 * get the price for this stock entry
	 * @return double
	 */
	public double getPrice()
	{
		return this.price;
	}

	/**
	 * get the amount for this stock entry
	 * @return int
	 */
	public int getAmount()
	{
		return this.amount;
	}

	/**
	 * get the usable amount for this stock entry
	 * @return int
	 */
	public int getUsableAmount()
	{
		int used = 0;

		for (int i = 0; i < this.usedAmounts.size(); i++)
			used += this.usedAmounts.get(i);

		return this.amount - used;
	}

	/**
	 * add a used amount to this stock entry
	 * @param amount
	 */
	public void addUsedAmount(int amount)
	{
		this.usedAmounts.add(amount);
	}

	/**
	 * get all used amounts
	 * @return ArrayList
	 */
	public ArrayList<Integer> getUsedAmounts()
	{
		return this.usedAmounts;
	}
}