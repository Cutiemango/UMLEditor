package me.Cutiemango.UMLEditor.objects.basic;

import me.Cutiemango.UMLEditor.Pair;
import me.Cutiemango.UMLEditor.objects.BaseObject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static me.Cutiemango.UMLEditor.ConfigSettings.DEFAULT_FONT;
import static me.Cutiemango.UMLEditor.ConfigSettings.DEFAULT_OBJECT_NAME;
import static me.Cutiemango.UMLEditor.ConfigSettings.PORT_SIZE;

public class BasicObject extends BaseObject
{
	protected BasicObject(int x, int y) {
		super(x, y);
	}

	protected String objectName = DEFAULT_OBJECT_NAME;
	protected int width;
	protected int height;

	protected List<Pair<Integer, Integer>> portLocations = new ArrayList<>();

	@Override
	public void draw(Graphics g) {
		drawPorts(g);
		drawName(g);
	}


	public List<Pair<Integer, Integer>> getPortLocations() {
		// lazy evaluation
		if (portLocations.isEmpty()){
			// upper
			portLocations.add(new Pair<>(x + width / 2 - PORT_SIZE / 2, y - PORT_SIZE));
			// right
			portLocations.add(new Pair<>(x + width, y + height / 2 - PORT_SIZE / 2));
			// lower
			portLocations.add(new Pair<>(x + width / 2 - PORT_SIZE / 2, y + height));
			// left
			portLocations.add(new Pair<>(x - PORT_SIZE, y + height / 2 - PORT_SIZE / 2));
		}
		return portLocations;
	}

	private void drawPorts(Graphics g) {
		for (Pair<Integer, Integer> pair : portLocations) {
			g.fillRect(pair.getKey(), pair.getValue(), PORT_SIZE, PORT_SIZE);
		}
	}

	private void drawName(Graphics g) {
		int stringWidth = g.getFontMetrics(DEFAULT_FONT).stringWidth(objectName);
		g.setFont(DEFAULT_FONT);
		g.drawString(objectName, x + (width - stringWidth) / 2, y + 25);
	}

}
