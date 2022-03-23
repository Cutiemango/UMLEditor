package me.Cutiemango.UMLEditor.components;

import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.mode.ToolMode;
import me.Cutiemango.UMLEditor.objects.BaseObject;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import static me.Cutiemango.UMLEditor.ConfigSettings.GROUP_SELECTED_COLOR;

public class UMLCanvas
{
	private final JPanel canvas = new JPanel()
	{
		@Override
		public void paint(Graphics g) {
			draw(g);
		}
	};

	private int dragStartX, dragStartY, dragWidth, dragHeight;
	private boolean showArea = false;

	public JPanel getCanvas() {
		return canvas;
	}

	public void switchMode(ToolMode prevMode, ToolMode newMode) {
		canvas.removeMouseListener(prevMode);
		canvas.removeMouseMotionListener(prevMode);

		canvas.addMouseListener(newMode);
		canvas.addMouseMotionListener(newMode);
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = canvas.getSize();

		g2d.setColor(new Color(0x202020));
		g2d.fillRect(0, 0, size.width, size.height);

		g2d.setColor(new Color(0xffffff));
		g2d.setStroke(new BasicStroke(1));

		for (BaseObject object : UMLEditor.getObjects()) {
			object.draw(g2d);
		}

		if (showArea) {
			g2d.setColor(GROUP_SELECTED_COLOR);
			g2d.fillRect(dragStartX, dragStartY, dragWidth, dragHeight);
		}
	}

	public void repaint() {
		canvas.repaint();
	}



	public void setSelectedArea(int x, int y, int width, int height) {
		this.dragStartX = x;
		this.dragStartY = y;
		this.dragWidth = width;
		this.dragHeight = height;
	}

	public void setShowArea(boolean showArea) {
		this.showArea = showArea;
	}
}
