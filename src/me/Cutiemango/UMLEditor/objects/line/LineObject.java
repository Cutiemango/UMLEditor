package me.Cutiemango.UMLEditor.objects.line;

import me.Cutiemango.UMLEditor.objects.BaseObject;

import java.awt.Graphics;
import java.awt.Point;

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
		super.draw(g);
		g.drawLine(x, y, hx, hy);
		decorateHead(g);
	}

	public Point getHead() {
		return new Point(hx, hy);
	}

	public Point getTail() {
		return new Point(x, y);
	}

	public void setHead(int x, int y) {
		this.hx = x;
		this.hy = y;
	}

	public void setTail(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// either head (arrow side) or tail
	public boolean isSelectingTail() {
		return isSelectingTail;
	}
}
