package me.Cutiemango.UMLEditor.components;

import me.Cutiemango.UMLEditor.ConfigSettings;
import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.objects.GroupedObject;
import me.Cutiemango.UMLEditor.objects.basic.BasicObject;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Point;
import java.util.List;

public class UMLMenuBar
{
	public UMLMenuBar() {
		final JMenu fileMenu = new JMenu("File");

		final JMenu editMenu = new JMenu("Edit");
		final JMenuItem menuItem = new JMenuItem("Change Object Name");
		menuItem.addActionListener(e -> handleChangeObjectName());
		editMenu.add(menuItem);

		final JMenuItem groupItem = new JMenuItem("Group");
		groupItem.addActionListener(e -> groupObject());
		editMenu.add(groupItem);

		final JMenuItem ungroupItem = new JMenuItem("Ungroup");
		ungroupItem.addActionListener(e -> ungroupObject());
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

	private void groupObject() {
		List<BasicObject> objects = UMLEditor.getCanvas().getObjectsInArea();
		if (objects.size() <= 1) {
			JOptionPane.showMessageDialog(null, "Select at least 2 objects to group!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Point upperLeft = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		Point lowerRight = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		objects.forEach(object -> {
			upperLeft.x = Math.min(upperLeft.x, object.getX());
			upperLeft.y = Math.min(upperLeft.y, object.getY());
			lowerRight.x = Math.max(lowerRight.x, object.getDiagonalX());
			lowerRight.y = Math.max(lowerRight.y, object.getDiagonalY());
		});

		objects.stream().flatMap(object -> object.getPorts().stream()).forEach(port -> {
			upperLeft.x = Math.min(upperLeft.x, port.getX());
			upperLeft.y = Math.min(upperLeft.y, port.getY());
			lowerRight.x = Math.max(lowerRight.x, port.getDiagonalX());
			lowerRight.y = Math.max(lowerRight.y, port.getDiagonalY());
		});

		objects.forEach(o -> o.setGrouped(true));

		GroupedObject groupedObject = new GroupedObject(upperLeft.x, upperLeft.y, lowerRight.x - upperLeft.x,
														lowerRight.y - upperLeft.y, objects);
		UMLEditor.addObject(groupedObject);
		UMLEditor.resetSelection();
		UMLEditor.setSelectedObject(groupedObject);
		UMLEditor.getCanvas().setShowArea(false);
		UMLEditor.getCanvas().repaint();
	}

	private void ungroupObject() {
		UMLEditor.getSelectedObject().filter(o -> o instanceof GroupedObject).ifPresentOrElse(o -> {
			GroupedObject groupedObject = (GroupedObject) o;
			groupedObject.ungroup();
			UMLEditor.resetSelection();
			UMLEditor.getCanvas().setShowArea(false);
			UMLEditor.getCanvas().repaint();
		}, () -> JOptionPane.showMessageDialog(null,
											   "No object selected or the selected object is not a grouped object!",
											   "Error", JOptionPane.ERROR_MESSAGE));
	}

}
