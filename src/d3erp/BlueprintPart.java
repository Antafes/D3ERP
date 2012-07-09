package d3erp;

/**
 *
 * @author Neithan
 */
public class BlueprintPart
{
	private Ingredient ingredient;
	private int amount;

	/**
	 * @param ingredient
	 */
	public BlueprintPart(Ingredient ingredient)
	{
		this.ingredient = ingredient;
		this.amount = 0;
	}

	/**
	 * get the ingredient for this blueprint part
	 * @return Ingredient
	 */
	public Ingredient getIngredient()
	{
		return this.ingredient;
	}

	/**
	 * get the amount for this blueprint part
	 * @return int
	 */
	public int getAmount()
	{
		return this.amount;
	}

	/**
	 * set the amount for this blueprint part
	 * @param amount
	 */
	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	/**
	 * add an amount to this blueprint part
	 * @param amount
	 */
	public void addAmount(int amount)
	{
		this.amount += amount;
	}

	/**
	 * remove an amount from this blueprint part
	 * @param amount
	 */
	public void removeAmount(int amount)
	{
		this.amount -= amount;
	}
}