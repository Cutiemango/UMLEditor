package me.Cutiemango.UMLEditor.objects;

import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.objects.basic.BasicObject;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import static me.Cutiemango.UMLEditor.ConfigSettings.GROUP_BORDER_COLOR;
import static me.Cutiemango.UMLEditor.ConfigSettings.GROUP_SELECTED_COLOR;

public class GroupedObject extends BasicObject
{
	public GroupedObject(int x, int y, int width, int height, List<BasicObject> objects) {
		super(x, y, width, height);
		this.objects = objects;
		this.objectName = null; // hides objects name
		this.ports = new ArrayList<>(); // no ports
	}

	private final List<BasicObject> objects;

	public void ungroup() {
		for (BasicObject object : objects) {
			object.setGrouped(false);
		}
		UMLEditor.getCanvas().removeObject(this);
	}

	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		for (BasicObject object : objects)
			object.setSelected(selected);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if (isSelected) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(GROUP_SELECTED_COLOR);
			g2d.fillRect(x, y, width, height);
			g2d.setColor(GROUP_BORDER_COLOR);
			g2d.setStroke(new BasicStroke(2));
			g2d.drawRect(x, y, width, height);
		}
	}

	@Override
	public boolean includesPoint(int x, int y) {
		return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
	}

	@Override
	public boolean isWithinArea(int x, int y, int width, int height) {
		return x <= this.x && x + width >= this.x + this.width && y <= this.y && y + height >= this.y + this.height;
	}

	@Override
	public void moveTo(int x, int y) {
		int dx = x - this.x, dy = y - this.y;
		for (BasicObject object : objects) {
			object.moveTo(object.getX() + dx, object.getY() + dy);
		}

		super.moveTo(x, y);
	}

	@Override
	public String toString() {
		return "GroupedObject [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
	}
}
