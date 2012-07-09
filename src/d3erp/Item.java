package d3erp;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Neithan
 */
public class Item
{
	private int id;
	private String name;
	private Blueprint blueprint;

	/**
	 * @param id
	 * @param name
	 * @param blueprint
	 */
	public Item(int id, String name, Blueprint blueprint)
	{
		this.id = id;
		this.name = name;
		this.blueprint = blueprint;
	}

	/**
	 * get the item id
	 * @return int
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 * get the blueprint from which this item could be build
	 * @return Blueprint
	 */
	public Blueprint getBlueprint()
	{
		return this.blueprint;
	}

	/**
	 * get the item name
	 * @return String
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Calculates the producible amount for this item.
	 * Returns a HashMap containing a list of price and amount pairs.
	 * Each pair determines how many items could be built for this price.
	 * @return HashMap
	 */
	public HashMap<Double, Integer> calculateProducibleAmount()
	{
		HashMap<Double, Integer> returning = new HashMap<>();
		HashMap<Integer, HashMap<Double, Integer>> list = new HashMap<>();

		for (int i = 0; i < this.blueprint.getParts().size(); i++)
		{
			HashMap<Double, Integer> amounts = new HashMap<>();
			BlueprintPart part = this.blueprint.getParts().get(i);

			for (int x = 0; x < part.getIngredient().getInventory().size(); x++)
			{
				Ingredient ingredient = part.getIngredient();
				int amount = (int) Math.floor(ingredient.getInventory().get(x).getUsableAmount() / part.getAmount());
				amounts.put(ingredient.getInventory().get(x).getPrice(), amount);
			}

			list.put(part.getIngredient().getId(), amounts);
		}

		HashMap<Integer, Integer> iteratorList = new HashMap<>();
		HashMap<Integer, Double> ingredientAmounts = new HashMap<>();
		outer:while (true)
		{
			for (Map.Entry<Integer, HashMap<Double, Integer>> entry : list.entrySet())
			{
				Integer ingredientID = entry.getKey();
				HashMap<Double, Integer> amounts = entry.getValue();

				boolean isEmpty = true;
				for (Map.Entry<Double, Integer> testEntry : amounts.entrySet())
				{
					Integer testAmount = testEntry.getValue();

					if (testAmount > 0)
						isEmpty = false;
				}

				if (isEmpty)
					break outer;

				if (!iteratorList.containsKey(ingredientID))
					iteratorList.put(ingredientID, 0);
				int iterator = iteratorList.get(ingredientID);

				ArrayList<Double> keyList = new ArrayList<>();
				keyList.addAll(amounts.keySet());
				Collections.sort(keyList);

				ingredientAmounts.put(ingredientID, keyList.get(iterator));

				if (list.size() == ingredientAmounts.size())
				{
					double totalPrice = 0;
					int amount = 0;

					for (Map.Entry<Integer, Double> amountEntry : ingredientAmounts.entrySet())
					{
						Integer key = amountEntry.getKey();
						Double price = amountEntry.getValue();

						totalPrice += price;

						if (amount == 0 || amount > list.get(key).get(price))
							amount = list.get(key).get(price);
					}

					returning.put(totalPrice, amount);

					ArrayList<Integer> deleteKeys = new ArrayList<>();
					for (Map.Entry<Integer, Double> amountEntry : ingredientAmounts.entrySet())
					{
						Integer key = amountEntry.getKey();
						Double price = amountEntry.getValue();
						int listAmount = list.get(key).get(price);

						if (list.get(key).get(price).equals(amount))
						{
							int iter = iteratorList.get(key);
							iter++;
							iteratorList.put(key, iter);
							deleteKeys.add(key);
						}

						list.get(key).put(price, listAmount - amount);
					}

					for (int i = 0; i < deleteKeys.size(); i++)
						ingredientAmounts.remove(deleteKeys.get(i));
				}
			}
		}

		return returning;
	}
}