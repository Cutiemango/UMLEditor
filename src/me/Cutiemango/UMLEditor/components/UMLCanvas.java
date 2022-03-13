package me.Cutiemango.UMLEditor.components;

import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.objects.BaseObject;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UMLCanvas
{
	private final JPanel canvas = new JPanel()
	{
		@Override
		public void paint(Graphics g) {
			draw(g);
		}
	};

	public JPanel getCanvas() {
		return canvas;
	}

	public void draw(Graphics g) {
		Dimension size = canvas.getSize();
		g.setColor(new Color(0x202020));
		g.fillRect(0, 0, size.width, size.height);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(0xffffff));
		g2d.setStroke(new BasicStroke(1));

		for (BaseObject object : UMLEditor.getObjects()) {
			object.draw(g2d);
		}
	}

	public void repaint() {
		canvas.repaint();
	}

}
