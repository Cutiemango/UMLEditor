package me.Cutiemango.UMLEditor.objects;

import me.Cutiemango.UMLEditor.UMLEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import static me.Cutiemango.UMLEditor.ConfigSettings.OBJECT_SELECTED_COLOR;

public class BaseObject
{
	protected BaseObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// abstract coordinate of the object
	protected int x, y;
	protected String iconPath;
	protected boolean isSelected = false;

	public void draw(Graphics g) {
		if (isSelected) {
			g.setColor(OBJECT_SELECTED_COLOR);
		} else {
			g.setColor(new Color(0xffffff));
		}
	}

	public boolean isWithin(int x, int y) {
		return false; // implementation is left to the subclasses
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	public String getIconPath() {
		return iconPath;
	}

	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean selected) {
		isSelected = selected;
	}

	@Override
	public String toString() {
		return "BaseObject [x=" + x + ", y=" + y + ", iconPath=" + iconPath + "]";
	}
}
