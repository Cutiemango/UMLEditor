package me.Cutiemango.UMLEditor.objects.basic;

import java.awt.Graphics;

import static me.Cutiemango.UMLEditor.ConfigSettings.USECASE_OBJECT_HEIGHT;
import static me.Cutiemango.UMLEditor.ConfigSettings.USECASE_OBJECT_WIDTH;

public class UseCaseObject extends BasicObject
{
	public UseCaseObject(int x, int y) {
		this(x, y, USECASE_OBJECT_WIDTH, USECASE_OBJECT_HEIGHT);
	}

	private UseCaseObject(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public BasicObject createObject(int x, int y) {
		return new UseCaseObject(x, y);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawOval(x, y, width, height);
	}

	@Override
	public boolean includesPoint(int x, int y) {
		// check if x and y is inside ellipse
		int rx = this.width / 2;
		int ry = this.height / 2;
		double h = this.x + rx;
		double k = this.y + ry;
		return (x - h) * (x - h) / (rx * rx) + (y - k) * (y - k) / (ry * ry) <= 1;
	}

	@Override
	public boolean isWithinArea(int x, int y, int width, int height) {
		return x <= this.x && x + width >= this.x + this.width && y <= this.y && y + height >= this.y + this.height;
	}

	@Override
	public String toString() {
		return "UseCaseObject [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
	}
}
