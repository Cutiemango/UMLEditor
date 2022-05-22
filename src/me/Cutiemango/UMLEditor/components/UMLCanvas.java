package me.Cutiemango.UMLEditor.components;

import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.mode.ToolMode;
import me.Cutiemango.UMLEditor.objects.BaseObject;
import me.Cutiemango.UMLEditor.objects.Port;
import me.Cutiemango.UMLEditor.objects.basic.BasicObject;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static me.Cutiemango.UMLEditor.ConfigSettings.CANVAS_BACKGROUND_COLOR;
import static me.Cutiemango.UMLEditor.ConfigSettings.GROUP_BORDER_COLOR;
import static me.Cutiemango.UMLEditor.ConfigSettings.GROUP_SELECTED_COLOR;

public class UMLCanvas
{
	private final JPanel canvas = new JPanel()
	{
		@Override
		public void paint(Graphics g) {
			draw(g);
		}
	};

	private final List<BaseObject> objects = new ArrayList<>();
	private BaseObject selectedObject = null;
	private ToolMode currentMode = null;

	private int areaX, areaY, areaWidth, areaHeight;
	private boolean showArea = false;

	public JPanel getCanvas() {
		return canvas;
	}

	public List<BaseObject> getObjects() {
		return objects;
	}

	public void addObject(BaseObject object) {
		objects.add(object);
	}

	public void removeObject(BaseObject object) {
		objects.remove(object);
	}

	public Optional<BaseObject> getSelectedObject() {
		return Optional.ofNullable(selectedObject);
	}

	public void setSelectedObject(BaseObject object) {
		resetSelection();
		System.out.println("Selected object: " + object);
		if (object != null) {
			object.setSelected(true);
			selectedObject = object;
		}
	}

	public void resetSelection() {
		objects.forEach(obj -> obj.setSelected(false));
		if (selectedObject != null) {
			selectedObject.setSelected(false);
		}
		selectedObject = null;
	}

	// find the nearest port location with respect to (x, y)
	public Optional<Port> findPort(int x, int y) {
		return objects.stream().filter(o -> o instanceof BasicObject && o.includesPoint(x, y))
							   .map(o -> ((BasicObject) o).getPorts()).flatMap(Collection::stream)
							   .min(Comparator.comparingInt(port -> (port.getX() - x) * (port.getX() - x) + (port.getY() - y) * (port.getY() - y)));
	}

	public void switchMode(ToolMode newMode) {
		if (currentMode != null) {
			canvas.removeMouseListener(currentMode);
			canvas.removeMouseMotionListener(currentMode);
		}

		canvas.addMouseListener(newMode);
		canvas.addMouseMotionListener(newMode);
		currentMode = newMode;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = canvas.getSize();

		g2d.setColor(CANVAS_BACKGROUND_COLOR);
		g2d.fillRect(0, 0, size.width, size.height);

		g2d.setColor(new Color(0xffffff));
		g2d.setStroke(new BasicStroke(1));

		for (BaseObject object : objects) {
			object.draw(g2d);
		}

		if (showArea) {
			g2d.setColor(GROUP_SELECTED_COLOR);
			g2d.fillRect(areaX, areaY, areaWidth, areaHeight);
			g2d.setColor(GROUP_BORDER_COLOR);
			g2d.setStroke(new BasicStroke(2));
			g2d.drawRect(areaX, areaY, areaWidth, areaHeight);
		}
	}

	public void repaint() {
		canvas.repaint();
	}

	public List<BasicObject> getObjectsInArea() {
		return objects.stream()
						.filter(obj -> obj instanceof BasicObject)
						.map(obj -> (BasicObject) obj)
						.filter(obj -> !obj.isGrouped() && obj.isWithinArea(areaX, areaY, areaWidth, areaHeight))
						.collect(Collectors.toList());
	}

	public void setSelectedArea(int x, int y, int width, int height) {
		this.areaX = x;
		this.areaY = y;
		this.areaWidth = width;
		this.areaHeight = height;
	}

	public void setShowArea(boolean showArea) {
		this.showArea = showArea;
	}
}
