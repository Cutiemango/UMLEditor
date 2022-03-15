package me.Cutiemango.UMLEditor.objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static me.Cutiemango.UMLEditor.ConfigSettings.PORT_SIZE;

public class Port extends BaseObject
{
	public Port(int x, int y, BaseObject parent) {
		super(x, y);
		this.parent = parent;
	}

	private final BaseObject parent;
	private Port parentPort;

	// other ports that are "attached" to this port
	private final List<Port> childPorts = new ArrayList<>();

	@Override
	public void draw(Graphics g) {
		g.fillRect(x, y, PORT_SIZE, PORT_SIZE);
	}

	@Override
	public void moveTo(int x, int y) {
		super.moveTo(x, y);
		for (Port port : childPorts) {
			port.moveTo(x, y);
		}
	}

	@Override
	public boolean isWithin(int x, int y) {
		return x >= this.x && x <= this.x + PORT_SIZE && y >= this.y && y <= this.y + PORT_SIZE;
	}

	public BaseObject getParent() {
		return parent;
	}

	public BaseObject getConnectedObject() {
		return parentPort.getParent();
	}

	public void connect(Port parent) {
		if (parentPort != null) {
			parentPort.childPorts.remove(this);
		}
		parentPort = parent;
		parentPort.childPorts.add(this);

		this.x = parent.x;
		this.y = parent.y;
	}
}
