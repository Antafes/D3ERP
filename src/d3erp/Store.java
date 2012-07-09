/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package d3erp;

import java.util.HashMap;

/**
 *
 * @author Neithan
 */
public class Store
{
	private HashMap<Integer, Ingredient> ingredients;
	private HashMap<Integer, Item> items;
	private HashMap<Integer, StorePosition> positions;

	private Store()
	{
		this.ingredients = new HashMap<>();
		this.items = new HashMap<>();
		this.positions = new HashMap<>();
	}

	/**
	 * get the Store instance
	 * @return Store
	 */
	public static Store getInstance()
	{
        return StoreHolder.INSTANCE;
    }

    private static class StoreHolder
	{
        private static final Store INSTANCE = new Store();
    }

	/**
	 * get all ingredients
	 * @return HashMap
	 */
	public HashMap<Integer, Ingredient> getIngredients()
	{
		return this.ingredients;
	}

	/**
	 * get the ingredient with the given ingredient id
	 * @param ingredientID
	 * @return Ingredient
	 */
	public Ingredient getIngredient(int ingredientID)
	{
		return this.ingredients.get(ingredientID);
	}

	/**
	 * add an ingredient via name
	 * @param name
	 * @return int
	 */
	public int addIngredient(String name)
	{
		int key = this.ingredients.size();
		this.addIngredient(key, name);

		return key;
	}

	/**
	 * add an ingredient via ingredient id and name
	 * @param ingredientID
	 * @param name
	 */
	public void addIngredient(int ingredientID, String name)
	{
		Ingredient ingredient = new Ingredient(ingredientID, name);
		this.ingredients.put(ingredientID, ingredient);
	}

	/**
	 * remove the ingredient with the given id
	 * @param ingredientID
	 */
	public void removeIngredient(int ingredientID)
	{
		if (this.items.containsKey(ingredientID))
			this.items.remove(ingredientID);
	}

	/**
	 * remove the given ingredient
	 * @param ingredient
	 */
	public void removeIngredient(Ingredient ingredient)
	{
		this.removeItem(ingredient.getId());
	}

	/**
	 * get all items
	 * @return HashMap
	 */
	public HashMap<Integer, Item> getItems()
	{
		return this.items;
	}

	/**
	 * get the item with the given item id
	 * @param itemID
	 * @return
	 */
	public Item getItem(int itemID)
	{
		return this.items.get(itemID);
	}

	/**
	 * add an item via name and blueprint
	 * @param name
	 * @param blueprint
	 */
	public void addItem(String name, Blueprint blueprint)
	{
		int key = this.items.size();
		this.addItem(key, name, blueprint);
	}

	/**
	 * add an item via item id, name and blueprint
	 * @param key
	 * @param name
	 * @param blueprint
	 */
	public void addItem(int key, String name, Blueprint blueprint)
	{
		Item item = new Item(key, name, blueprint);
		this.items.put(key, item);
	}

	/**
	 * remove the item with the given item id
	 * @param key
	 */
	public void removeItem(int key)
	{
		if (this.items.containsKey(key))
			this.items.remove(key);
	}

	/**
	 * remove the given item
	 * @param item
	 */
	public void removeItem(Item item)
	{
		this.removeItem(item.getId());
	}

	/**
	 * get all positions
	 * @return HashMap
	 */
	public HashMap<Integer, StorePosition> getPositions()
	{
		return positions;
	}

	/**
	 * get the position with the given position id
	 * @param positionID
	 * @return
	 */
	public StorePosition getPosition(int positionID)
	{
		return this.positions.get(positionID);
	}

	/**
	 * add a position via item
	 * @param item
	 */
	public void addPosition(Item item)
	{
		int key = this.positions.size();
		this.addPosition(key, item);
	}

	/**
	 * add a position via position id and item
	 * @param positionID
	 * @param item
	 */
	public void addPosition (int positionID, Item item)
	{
		StorePosition position = new StorePosition(positionID, item);
		this.positions.put(positionID, position);
	}

	/**
	 * remove the position with the given position id
	 * @param positionID
	 */
	public void removePosition(int positionID)
	{
		if (this.positions.containsKey(positionID))
			this.positions.remove(positionID);
	}

	/**
	 * remove the given position
	 * @param position
	 */
	public void removePosition(StorePosition position)
	{
		this.removePosition(position.getId());
	}
}