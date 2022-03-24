package me.Cutiemango.UMLEditor.components;

import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.objects.basic.BasicObject;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class UMLMenuBar
{
	public UMLMenuBar() {
		final JMenu fileMenu = new JMenu("File");

		final JMenu editMenu = new JMenu("Edit");
		final JMenuItem menuItem = new JMenuItem("Change Object Name");
		menuItem.addActionListener(e -> handleChangeObjectName());
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

	private void handleChangeObjectName() {
		UMLEditor.getSelectedObject().filter(o -> o instanceof BasicObject).ifPresentOrElse(o -> {
			BasicObject obj = (BasicObject) o;
			String name = JOptionPane.showInputDialog("Enter new object name", obj.getObjectName());
			if (name != null) {
				obj.setObjectName(name);
				UMLEditor.getCanvas().repaint();
			}
		}, () -> JOptionPane.showMessageDialog(null, "No object selected!", "Error", JOptionPane.ERROR_MESSAGE));
	}

}
