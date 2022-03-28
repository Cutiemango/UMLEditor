package me.Cutiemango.UMLEditor.objects.line;

import java.awt.Graphics;

public class CompositionLine extends LineObject
{
	public CompositionLine(int x, int y, int hx, int hy) {
		super(x, y, hx, hy);
	}

	@Override
	public LineObject createObject(int x, int y, int hx, int hy) {
		return new CompositionLine(x, y, hx, hy);
	}

	@Override
	public void decorateHead(Graphics g) {

	}

	@Override
	public String toString() {
		return "CompositionLine [x=" + x + ", y=" + y + "]";
	}
}
