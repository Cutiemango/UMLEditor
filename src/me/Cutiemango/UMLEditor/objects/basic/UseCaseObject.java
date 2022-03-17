package me.Cutiemango.UMLEditor.objects.basic;

import java.awt.Graphics;

import static me.Cutiemango.UMLEditor.ConfigSettings.USECASE_HEIGHT;
import static me.Cutiemango.UMLEditor.ConfigSettings.USECASE_WIDTH;

public class UseCaseObject extends BasicObject
{
	public UseCaseObject(int x, int y) {
		this(x, y, USECASE_WIDTH, USECASE_HEIGHT);
	}

	private UseCaseObject(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.iconPath = "/assets/usecase.png";
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
	public boolean isWithin(int x, int y) {
		// check if x and y is inside ellipse
		int rx = this.width / 2;
		int ry = this.height / 2;
		double h = this.x + rx;
		double k = this.y + ry;
		return (x - h) * (x - h) / (rx * rx) + (y - k) * (y - k) / (ry * ry) <= 1;
	}
}
