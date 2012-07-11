package d3erp;

import java.io.*;
import java.nio.file.Paths;
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
	public static String PATH = Paths.get(System.getProperty("user.home"), ".d3erp").toString();

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