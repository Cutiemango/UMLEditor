package me.Cutiemango.UMLEditor.objects.basic;

import java.awt.Graphics;

import static me.Cutiemango.UMLEditor.ConfigSettings.CLASS_OBJECT_HEIGHT;
import static me.Cutiemango.UMLEditor.ConfigSettings.CLASS_OBJECT_WIDTH;

public class ClassObject extends BasicObject
{
	public ClassObject(int x, int y) {
		this(x, y, CLASS_OBJECT_WIDTH, CLASS_OBJECT_HEIGHT);
	}

	private ClassObject(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public BasicObject createObject(int x, int y) {
		return new ClassObject(x, y);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawRect(x, y, width, height);
		g.drawLine(x, y + height / 3, x + width, y + height / 3);
		g.drawLine(x, y + height / 3 * 2, x + width, y + height / 3 * 2);
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
	public String toString() {
		return "ClassObject [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
	}
}
