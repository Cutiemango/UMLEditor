package me.Cutiemango.UMLEditor.objects.basic;

import java.awt.Graphics;

import static me.Cutiemango.UMLEditor.ConfigSettings.USECASE_HEIGHT;
import static me.Cutiemango.UMLEditor.ConfigSettings.USECASE_WIDTH;

public class UseCaseObject extends BasicObject
{
	public UseCaseObject(int x, int y) {
		super(x, y);
		this.width = USECASE_WIDTH;
		this.height = USECASE_HEIGHT;
		this.iconPath = "/assets/usecase.png";
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
