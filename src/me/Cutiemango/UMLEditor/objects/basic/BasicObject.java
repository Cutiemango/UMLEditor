package me.Cutiemango.UMLEditor.objects.basic;

import me.Cutiemango.UMLEditor.Pair;
import me.Cutiemango.UMLEditor.objects.BaseObject;
import me.Cutiemango.UMLEditor.objects.Port;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static me.Cutiemango.UMLEditor.ConfigSettings.DEFAULT_FONT;
import static me.Cutiemango.UMLEditor.ConfigSettings.DEFAULT_OBJECT_NAME;
import static me.Cutiemango.UMLEditor.ConfigSettings.PORT_SIZE;

public class BasicObject extends BaseObject
{
	protected BasicObject(int x, int y, int width, int height) {
		super(x, y);

		this.width = width;
		this.height = height;

		ports.add(new Port(x + width / 2 - PORT_SIZE / 2, y - PORT_SIZE, this));
		ports.add(new Port(x + width, y + height / 2 - PORT_SIZE / 2, this));
		ports.add(new Port(x + width / 2 - PORT_SIZE / 2, y + height, this));
		ports.add(new Port(x - PORT_SIZE, y + height / 2 - PORT_SIZE / 2, this));
	}

	protected String objectName = DEFAULT_OBJECT_NAME;
	protected int width;
	protected int height;

	protected List<Port> ports = new ArrayList<>();

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if (isSelected) {
			for (Port port : ports) {
				port.draw(g);
			}
		}
		drawName(g);
	}

	@Override
	public void moveTo(int x, int y) {
		int dx = x - this.x, dy = y - this.y;
		super.moveTo(x, y);
		for (Port port : ports) {
			port.moveTo(port.getX() + dx, port.getY() + dy);
		}
	}

	public List<Port> getPorts() {
		return ports;
	}

	private void drawName(Graphics g) {
		int stringWidth = g.getFontMetrics(DEFAULT_FONT).stringWidth(objectName);
		g.setFont(DEFAULT_FONT);
		g.drawString(objectName, x + (width - stringWidth) / 2, y + 25);
	}

}
