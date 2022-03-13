package me.Cutiemango.UMLEditor.objects.line;

import me.Cutiemango.UMLEditor.objects.BaseObject;

import java.awt.Graphics;

public class LineObject extends BaseObject
{
	protected LineObject(int x, int y, int hx, int hy) {
		super(x, y);
		this.hx = hx;
		this.hy = hy;
	}

	// coordinates of head
	protected int hx, hy;
	protected boolean isSelectingTail = true;

	public void decorateHead(Graphics g) {}

	@Override
	public void draw(Graphics g) {
		g.drawLine(x, y, hx, hy);
		decorateHead(g);
	}

	// either head (arrow side) or tail
	public boolean isSelectingTail() {
		return isSelectingTail;
	}
}
