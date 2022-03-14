package me.Cutiemango.UMLEditor.objects.basic;

import java.awt.Graphics;

import static me.Cutiemango.UMLEditor.ConfigSettings.CLASS_HEIGHT;
import static me.Cutiemango.UMLEditor.ConfigSettings.CLASS_WIDTH;

public class ClassObject extends BasicObject
{
	public ClassObject(int x, int y) {
		super(x, y);
		this.width = CLASS_WIDTH;
		this.height = CLASS_HEIGHT;
		this.iconPath = "/assets/class.png";
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawRect(x, y, width, height);
		g.drawLine(x, y + height / 3, x + width, y + height / 3);
		g.drawLine(x, y + height / 3 * 2, x + width, y + height / 3 * 2);
	}

	@Override
	public boolean isWithin(int x, int y) {
		return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
	}
}
