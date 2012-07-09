package d3erp;

import java.io.*;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neithan
 */
public class D3erp
{
	public static final String VERSION = "1.0";
	public static String PATH = System.getProperty("user.home") + "/.d3erp/";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
	{
		double javaVersion = Double.parseDouble(System.getProperty("java.version").substring(0, 3));

		if (javaVersion < 1.7)
		{
			WrongVersionDialog dialog = new WrongVersionDialog(null, false);
			dialog.setVisible(true);
		}
		else
		{
			StoreImporter importer = new StoreImporter();
			importer.importStore();
			GUIFrame gui = new GUIFrame();
			gui.setVisible(true);
		}

//		store.addIngredient("test");
//		store.addIngredient("test2");
//		store.getIngredients().get(0).addStock(1000, 13000 / 1000);
//		store.getIngredients().get(1).addStock(2000, 34000 / 2000);
//		store.getIngredients().get(0).addStock(500, 7000 / 500);
//		store.getIngredients().get(1).addStock(500, 8000 / 500);
//
//		Blueprint bp = new Blueprint(50000);
//		BlueprintPart bpp = new BlueprintPart(store.getIngredients().get(0));
//		bpp.setAmount(100);
//		BlueprintPart bpp2 = new BlueprintPart(store.getIngredients().get(1));
//		bpp2.setAmount(200);
//		bp.addPart(bpp);
//		bp.addPart(bpp2);
//		store.addItem("testitem", bp);
//		HashMap<Double, Integer> amounts = store.getItems().get(0).calculateProducibleAmount();
//
//		Blueprint bp2 = new Blueprint(50000);
//		BlueprintPart bpp3 = new BlueprintPart(store.getIngredients().get(0));
//		bpp3.setAmount(150);
//		BlueprintPart bpp4 = new BlueprintPart(store.getIngredients().get(1));
//		bpp4.setAmount(250);
//		bp2.addPart(bpp);
//		bp2.addPart(bpp2);
//		store.addItem("testitem2", bp2);
//
//		store.getIngredients().get(0).getInventory().get(0).addUsedAmount(5 * 150);
//		store.getIngredients().get(1).getInventory().get(0).addUsedAmount(5 * 250);
//
//		store.addPosition(store.getItems().get(0));
//		store.addPosition(store.getItems().get(1));
//		store.getPositions().get(0).setAmount(5);
//		store.getPositions().get(1).setAmount(10);
//
//		D3erp.saveStore(store);
    }

	public static Properties loadProperties()
	{
		File propertiesFile = new File(D3erp.PATH + "settings.xml");
		Properties props = new Properties();

		if (propertiesFile.exists())
		{
			try
			{
				try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(propertiesFile))) {
					props.loadFromXML(inputStream);
				}
			}
			catch (FileNotFoundException ex)
			{
				Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
			}
			catch (InvalidPropertiesFormatException ex)
			{
				Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
			}
			catch (IOException ex)
			{
				Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		else
		{
//			props.setProperty("useWebService", "true");
		}

		return props;
	}

	public static void saveProperties(Properties props)
	{
		File propertiesFile = new File(D3erp.PATH + "settings.xml");
		BufferedOutputStream outputStream;
		try
		{
			if (!propertiesFile.exists())
			{
				File path = new File(propertiesFile.getParent());
				path.mkdirs();
				propertiesFile.createNewFile();
			}

			outputStream = new BufferedOutputStream(new FileOutputStream(propertiesFile));
			props.storeToXML(outputStream, null);
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (InvalidPropertiesFormatException ex)
		{
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (IOException ex)
		{
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void saveStore()
	{
		StoreExporter exporter = new StoreExporter();
		exporter.export(Store.getInstance());
	}
}