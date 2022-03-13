package me.Cutiemango.UMLEditor.objects;

import java.awt.Graphics;

public class BaseObject
{
	protected BaseObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// abstract coordinate of the object
	protected int x, y;
	protected String iconPath;

	public void draw(Graphics g) {}

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
}
