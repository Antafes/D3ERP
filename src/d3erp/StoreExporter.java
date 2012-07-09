package d3erp;

import java.util.HashMap;
import java.util.Map;
import myXML.XMLWriter;
import org.w3c.dom.Element;

/**
 *
 * @author Neithan
 */
public class StoreExporter
{
	private boolean debug = true;
	private XMLWriter writer;

	public StoreExporter()
	{
		if (this.debug)
			this.writer = new XMLWriter("store");
		else
			this.writer = new XMLWriter("store.xsd", "store");
	}

	/**
	 * export the store to a xml file
	 * @param store
	 */
	public void export(Store store)
	{
		Element ingredientsElement = this.writer.addChild("ingredients");

		for (Map.Entry<Integer, Ingredient> entry : store.getIngredients().entrySet())
		{
			Integer key = entry.getKey();
			Ingredient ingredient = entry.getValue();
			HashMap<String, String> attributes = new HashMap<>();
			attributes.put("ingredientID", key.toString());
			Element ingredientElement = this.writer.addChild(ingredientsElement, "ingredient", attributes);
			this.writer.addChild(ingredientElement, "name", ingredient.getName());
			Element inventoryElement = this.writer.addChild(ingredientElement, "inventory");

			for (int i = 0; i < ingredient.getInventory().size(); i++)
			{
				Stock stock = ingredient.getInventory().get(i);
				Element stockElement = this.writer.addChild(inventoryElement, "stock");
				this.writer.addChild(stockElement, "amount", Integer.toString(stock.getAmount()));
				this.writer.addChild(stockElement, "price", Double.toString(stock.getPrice()));

				if (!stock.getUsedAmounts().isEmpty())
				{
					Element usedAmountsElement = this.writer.addChild(stockElement, "usedAmounts");

					for (int x = 0; x < stock.getUsedAmounts().size(); x++)
						this.writer.addChild(usedAmountsElement, "amount", Integer.toString(stock.getUsedAmounts().get(x)));
				}
			}
		}

		Element itemsElement = this.writer.addChild("items");

		for (Map.Entry<Integer, Item> entry : store.getItems().entrySet())
		{
			Integer key = entry.getKey();
			Item item = entry.getValue();
			HashMap<String, String> attributes = new HashMap<>();
			attributes.put("itemID", key.toString());
			Element itemElement = this.writer.addChild(itemsElement, "item", attributes);
			this.writer.addChild(itemElement, "name", item.getName());
			Element blueprintElement = this.writer.addChild(itemElement, "blueprint");
			this.writer.addChild(blueprintElement, "price", Double.toString(item.getBlueprint().getPrice()));
			Element partsElement = this.writer.addChild(blueprintElement, "parts");

			for (int i = 0; i < item.getBlueprint().getParts().size(); i++)
			{
				BlueprintPart part = item.getBlueprint().getParts().get(i);
				Element partElement = this.writer.addChild(partsElement, "part");
				this.writer.addChild(partElement, "ingredientID", Integer.toString(part.getIngredient().getId()));
				this.writer.addChild(partElement, "amount", Integer.toString(part.getAmount()));
			}
		}

		if (!store.getPositions().isEmpty())
		{
			Element positionsElement = this.writer.addChild("positions");

			for (Map.Entry<Integer, StorePosition> entry : store.getPositions().entrySet()) {
				Integer key = entry.getKey();
				StorePosition position = entry.getValue();
				HashMap<String, String> attributes = new HashMap<>();
				attributes.put("positionID", key.toString());
				Element positionElement = this.writer.addChild(positionsElement, "position", attributes);
				this.writer.addChild(positionElement, "itemID", Integer.toString(position.getItem().getId()));
				this.writer.addChild(positionElement, "amount", Integer.toString(position.getAmount()));
			}
		}

		this.writer.writeFile(D3erp.PATH + "store.xml");
	}
}