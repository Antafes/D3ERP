package d3erp;

import java.util.ArrayList;

/**
 *
 * @author Neithan
 */
public class Blueprint
{
	private ArrayList<BlueprintPart> parts;
	private double price;

	/**
	 * @param price
	 */
	public Blueprint(double price)
	{
		this.parts = new ArrayList<>();
		this.price = price;
	}

	/**
	 * get all parts of the blueprint
	 * @return ArrayList
	 */
	public ArrayList<BlueprintPart> getParts()
	{
		return this.parts;
	}

	/**
	 * add a BlueprintPart object to the blueprint
	 * @param part
	 */
	public void addPart(BlueprintPart part)
	{
		this.parts.add(part);
	}

	/**
	 * add a part via ingredient and amount to the blueprint
	 * @param ingredient
	 * @param amount
	 */
	public void addPart(Ingredient ingredient, int amount)
	{
		BlueprintPart part = new BlueprintPart(ingredient);
		part.addAmount(amount);

		this.addPart(part);
	}

	/**
	 * remove a part from the blueprint
	 * @param part
	 */
	public void removePart(BlueprintPart part)
	{
		this.parts.remove(part);
	}

	/**
	 * get the construction price for this blueprint
	 * @return double
	 */
	public double getPrice()
	{
		return this.price;
	}
}