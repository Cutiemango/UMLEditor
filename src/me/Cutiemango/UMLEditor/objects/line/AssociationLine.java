package me.Cutiemango.UMLEditor.objects.line;

import java.awt.Graphics;

public class AssociationLine extends LineObject
{
	public AssociationLine(int x, int y, int hx, int hy) {
		super(x, y, hx, hy);
	}

	@Override
	public LineObject createObject(int x, int y, int hx, int hy) {
		return new AssociationLine(x, y, hx, hy);
	}

	@Override
	public void decorateHead(Graphics g) {

	}

	@Override
	public String toString() {
		return "AssociationLine [x=" + x + ", y=" + y + "]";
	}
}
