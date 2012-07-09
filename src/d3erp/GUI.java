package d3erp;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Neithan
 */
public class GUI
{
	private JFrame window;
	private JMenuBar menuBar;

	public GUI()
	{
		this.window = new JFrame("D3ERP");
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.window.addWindowListener(new MainWindowListener());
		this.menuBar = new JMenuBar();
		this.window.setJMenuBar(this.menuBar);
		this.createMenu();
	}

	public void showGUI()
	{
		this.window.pack();
		this.window.setSize(800, 600);
		this.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.window.setVisible(true);
	}

	private void createMenu()
	{
		JMenu fileMenu = new JMenu("Datei");
		fileMenu.setMnemonic('d');

		JMenuItem produce = new JMenuItem("Herstellen");
		produce.setMnemonic('h');
		produce.addActionListener(new ProduceButton());
		fileMenu.add(produce);

		JMenuItem close = new JMenuItem("Beenden");
		close.setMnemonic('b');
		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		close.addActionListener(new CloseButton());
		fileMenu.add(close);

		this.menuBar.add(fileMenu);

		JMenu masterDataMenu = new JMenu("Stammdaten");
		masterDataMenu.setMnemonic('s');

		JMenuItem ingredients = new JMenuItem("Zutaten");
		ingredients.setMnemonic('z');
		ingredients.addActionListener(new IngredientsButton());
		masterDataMenu.add(ingredients);

		JMenuItem items = new JMenuItem("Gegenstände");
		items.setMnemonic('g');
		items.addActionListener(new ItemsButton());
		masterDataMenu.add(items);

		this.menuBar.add(masterDataMenu);
	}

	private class MainWindowListener extends WindowAdapter
	{

		@Override
		public void windowClosing(WindowEvent e)
		{
			D3erp.saveStore();
			super.windowClosing(e);
		}
	}

	private class CloseButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	private class IngredientsButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// @TODO: Anzeige der Zutaten inkl. Möglichkeit zum Hinzufügen von Zutaten
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	private class ItemsButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// @TODO: Anzeige der Gegenstände inkl. Möglichkeit zum Hinzufügen von Gegenständen
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	private class ProduceButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// @TODO: Anzeige der Herstellungsmaske
			System.out.println("klappt");
		}
	}
}