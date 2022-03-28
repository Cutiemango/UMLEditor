package me.Cutiemango.UMLEditor.objects;

import java.awt.Graphics;

import static me.Cutiemango.UMLEditor.ConfigSettings.DEFAULT_OBJECT_COLOR;
import static me.Cutiemango.UMLEditor.ConfigSettings.OBJECT_SELECTED_COLOR;

public class BaseObject
{
	protected BaseObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// abstract coordinate of the object
	protected int x, y;
	protected boolean isSelected = false;

	public void draw(Graphics g) {
		if (isSelected) {
			g.setColor(OBJECT_SELECTED_COLOR);
		} else {
			g.setColor(DEFAULT_OBJECT_COLOR);
		}
	}

	public boolean includesPoint(int x, int y) {
		return false; // implementation is left to the subclasses
	}

	public boolean isWithinArea(int x, int y, int width, int height) {
		return false; // implementation is left to the subclasses
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setSelected(boolean selected) {
		this.isSelected = selected;
	}

	@Override
	public String toString() {
		return "BaseObject [x=" + x + ", y=" + y + "]";
	}
}
