package d3erp;

/**
 *
 * @author Neithan
 */
public class StorePosition
{
	private int id;
	private Item item;
	private int amount;

	/**
	 * @param id
	 * @param item
	 */
	public StorePosition(int id, Item item)
	{
		this.id = id;
		this.item = item;
		this.amount = 0;
	}

	/**
	 * get the position id
	 * @return int
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 * get the item for this position
	 * @return Item
	 */
	public Item getItem()
	{
		return this.item;
	}

	/**
	 * get the amount for this position
	 * @return int
	 */
	public int getAmount()
	{
		return this.amount;
	}

	/**
	 * set the amount for this position
	 * @param amount
	 */
	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	/**
	 * add an amount to this position
	 * @param amount
	 */
	public void addAmount(int amount)
	{
		this.amount += amount;
	}

	/**
	 * remove an amount from this position
	 * @param amount
	 */
	public void removeAmount(int amount)
	{
		this.amount -= amount;
	}
}