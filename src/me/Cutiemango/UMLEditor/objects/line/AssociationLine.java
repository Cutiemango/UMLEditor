package me.Cutiemango.UMLEditor.objects.line;

import java.awt.Graphics;

public class AssociationLine extends LineObject
{
	public AssociationLine(int x, int y, int hx, int hy) {
		super(x, y, hx, hy);
		this.iconPath = "/assets/association.png";
	}

	@Override
	public void decorateHead(Graphics g) {

	}
}