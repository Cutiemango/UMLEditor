package me.Cutiemango.UMLEditor.objects.line;

import java.awt.Graphics;

public class GeneralizationLine extends LineObject
{
	public GeneralizationLine(int x, int y, int hx, int hy) {
		super(x, y, hx, hy);
	}

	@Override
	public LineObject createObject(int x, int y, int hx, int hy) {
		return new GeneralizationLine(x, y, hx, hy);
	}

	@Override
	public void decorateHead(Graphics g) {

	}

	@Override
	public String toString() {
		return "GeneralizationLine [x=" + x + ", y=" + y + "]";
	}
}
