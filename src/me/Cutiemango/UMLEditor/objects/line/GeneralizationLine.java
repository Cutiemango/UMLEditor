package me.Cutiemango.UMLEditor.objects.line;

import java.awt.Graphics;

public class GeneralizationLine extends LineObject
{
	public GeneralizationLine(int x, int y, int hx, int hy) {
		super(x, y, hx, hy);
		this.iconPath = "/assets/generalization.png";
	}

	@Override
	public void decorateHead(Graphics g) {

	}
}
