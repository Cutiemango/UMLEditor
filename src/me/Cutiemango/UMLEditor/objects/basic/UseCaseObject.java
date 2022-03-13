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
		g.drawOval(x, y, width, height);
	}
}
