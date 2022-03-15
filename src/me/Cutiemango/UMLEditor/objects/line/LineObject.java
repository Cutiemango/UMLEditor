package me.Cutiemango.UMLEditor.objects.line;

import me.Cutiemango.UMLEditor.objects.BaseObject;
import me.Cutiemango.UMLEditor.objects.Port;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static me.Cutiemango.UMLEditor.ConfigSettings.PORT_SIZE;

public class LineObject extends BaseObject
{
	protected LineObject(int x, int y, int hx, int hy) {
		super(x, y);

		ports.add(new Port(x, y, this));
		ports.add(new Port(hx, hy, this));
	}

	protected List<Port> ports = new ArrayList<>();
	protected boolean isSelectingTail = true;

	public void decorateHead(Graphics g) {}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		Port head = getHead(), tail = getTail();
		g.drawLine(head.getX() + PORT_SIZE / 2, head.getY() + PORT_SIZE / 2, tail.getX() + PORT_SIZE / 2, tail.getY() + PORT_SIZE / 2);
		decorateHead(g);
	}

	@Override
	public boolean isWithin(int x, int y) {
		return getHead().isWithin(x, y) || getTail().isWithin(x, y);
	}

	public Port getHead() {
		return ports.get(1);
	}

	public Port getTail() {
		return ports.get(0);
	}

	public Port getSelectingPort() {
		return isSelectingTail ? getTail() : getHead();
	}

	public Port getOtherPort() {
		return isSelectingTail ? getHead() : getTail();
	}

	public void setHead(Port other) {
		ports.set(1, other);
	}

	public void setTail(Port other) {
		ports.set(0, other);
	}

	@Override
	public void moveTo(int x, int y) {
		if (isSelectingTail()) {
			super.moveTo(x, y);
			getTail().moveTo(x, y);
		} else {
			getHead().moveTo(x, y);
		}
	}

	// either head (arrow side) or tail
	public boolean isSelectingTail() {
		return isSelectingTail;
	}

	public void setSelectingTail(boolean tail) {
		isSelectingTail = tail;
	}
}
