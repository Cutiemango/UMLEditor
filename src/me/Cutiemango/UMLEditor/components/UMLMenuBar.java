package me.Cutiemango.UMLEditor.components;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class UMLMenuBar
{
	public UMLMenuBar() {
		final JMenu fileMenu = new JMenu("File");

		final JMenu editMenu = new JMenu("Edit");
		final JMenuItem menuItem = new JMenuItem("Change Object Name");
		editMenu.add(menuItem);

		final JMenuItem groupItem = new JMenuItem("Group");
		editMenu.add(groupItem);

		final JMenuItem ungroupItem = new JMenuItem("Ungroup");
		editMenu.add(ungroupItem);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
	}

	private final JMenuBar menuBar = new JMenuBar();

	public JMenuBar getMenuBar() {
		return menuBar;
	}

}
