package d3erp;

import java.util.ArrayList;
import myXML.XMLParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Neithan
 */
public class StoreImporter
{
	private boolean debug = true;
	private XMLParser parser;

	public StoreImporter()
	{
		if (this.debug)
			this.parser = new XMLParser("src/d3erp/store.xsd");
		else
			this.parser = new XMLParser("store.xsd");
	}

	/**
	 * import a previously saved store
	 */
	public void importStore()
	{
		String path = D3erp.PATH + "store.xml";
		Store store = Store.getInstance();
		Document document;

		if (this.parser.parse(path))
		{
			document = this.parser.getDocument();
			Node storeNode = document.getChildNodes().item(0);
			Element storeElement = (Element) storeNode;

			NodeList ingredients = document.getElementsByTagName("ingredient");
			this.getIngredients(ingredients, store);

			NodeList items = document.getElementsByTagName("item");
			this.getItems(items, store);

			NodeList positions = document.getElementsByTagName("position");
			this.getPositions(positions, store);
		}
		else
			System.out.println(this.parser.getValidator().getLastException().getMessage());
	}

	private void getIngredients(NodeList ingredients, Store store)
	{
		for (int i = 0; i < ingredients.getLength(); i++)
		{
			Node ingredientNode = ingredients.item(i);

			if (ingredientNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element ingredientElement = (Element) ingredientNode;

				String name = XMLParser.getTagValue("name", ingredientElement);
				int ingredientID = Integer.parseInt(ingredientElement.getAttribute("ingredientID"));
				NodeList inventory = ingredientElement.getElementsByTagName("stock");
				store.addIngredient(ingredientID, name);
				this.getInventory(inventory, ingredientID, store);
			}
		}
	}

	private void getInventory(NodeList inventory, int ingredientID, Store store)
	{
		for (int i = 0; i < inventory.getLength(); i++)
		{
			Node stockNode = inventory.item(i);

			if (stockNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element stockElement = (Element) stockNode;

				int amount = XMLParser.getTagValueInt("amount", stockElement);
				double price = XMLParser.getTagValueDouble("price", stockElement);
				Stock stock = new Stock(amount, price);

				if (XMLParser.tagExists("usedAmounts", stockElement))
				{
					NodeList usedAmounts = stockElement.getElementsByTagName("usedAmounts");
					Node usedAmountsNode = usedAmounts.item(0);

					if (usedAmountsNode.getNodeType() == Node.ELEMENT_NODE)
					{
						Element usedAmountsElement = (Element) usedAmountsNode;
						ArrayList<Integer> usedAmountsList = XMLParser.getAllTagValuesInt("amount", usedAmountsElement);

						for (int x = 0; x < usedAmountsList.size(); x++)
							stock.addUsedAmount(usedAmountsList.get(x));
					}
				}

				store.getIngredient(ingredientID).addStock(stock);
			}
		}
	}

	private void getItems(NodeList items, Store store)
	{
		for (int i = 0; i < items.getLength(); i++)
		{
			Node itemNode = items.item(i);

			if (itemNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element itemElement = (Element) itemNode;

				String name = XMLParser.getTagValue("name", itemElement);
				int itemID = Integer.parseInt(itemElement.getAttribute("itemID"));
				Blueprint blueprint = this.getBlueprint(itemElement, store);
				store.addItem(itemID, name, blueprint);
			}
		}
	}

	private Blueprint getBlueprint(Element item, Store store)
	{
		Node blueprintNode = item.getElementsByTagName("blueprint").item(0);

		if (blueprintNode.getNodeType() == Node.ELEMENT_NODE)
		{
			Element blueprintElement = (Element) blueprintNode;

			Blueprint blueprint = new Blueprint(XMLParser.getTagValueDouble("price", blueprintElement));

			NodeList parts = blueprintElement.getElementsByTagName("part");

			for (int i = 0; i < parts.getLength(); i++)
			{
				Node partNode = parts.item(i);

				if (partNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element partElement = (Element) partNode;

					int ingredientID = XMLParser.getTagValueInt("ingredientID", partElement);
					int amount = XMLParser.getTagValueInt("amount", partElement);
					BlueprintPart part = new BlueprintPart(store.getIngredient(ingredientID));
					part.setAmount(amount);
					blueprint.addPart(part);
				}
			}

			return blueprint;
		}
		else
			return null;
	}

	private void getPositions(NodeList positions, Store store)
	{
		for (int i = 0; i < positions.getLength(); i++)
		{
			Node positionNode = positions.item(i);

			if (positionNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element positionElement = (Element) positionNode;

				int positionID = Integer.parseInt(positionElement.getAttribute("positionID"));
				int itemID = XMLParser.getTagValueInt("itemID", positionElement);
				int amount = XMLParser.getTagValueInt("amount", positionElement);

				store.addPosition(positionID, store.getItem(itemID));
				store.getPosition(positionID).setAmount(amount);
			}
		}
	}
}