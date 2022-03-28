package me.Cutiemango.UMLEditor.objects.line;

import java.awt.Graphics;

import static me.Cutiemango.UMLEditor.ConfigSettings.ARROW_SIZE;
import static me.Cutiemango.UMLEditor.ConfigSettings.PORT_SIZE;

public class GeneralizationLine extends LineObject
{
	public GeneralizationLine(int x, int y, int hx, int hy) {
		super(x, y, hx, hy);
	}

	@Override
	public LineObject createObject(int x, int y, int hx, int hy) {
		return new GeneralizationLine(x, y, hx, hy);
	}

	@Override
	public void decorateHead(Graphics g) {
		double dx = getHead().getX() - getTail().getX();
		double dy = getHead().getY() - getTail().getY();

		double d = Math.sqrt(dx * dx + dy * dy);
		double xm = d - ARROW_SIZE, xn = xm, x;
		double ym = ARROW_SIZE, yn = -ARROW_SIZE;

		double sin = dy / d, cos = dx / d;
		x = xm * cos - ym * sin + getTail().getX() + (PORT_SIZE / 2);
		ym = xm * sin + ym * cos + getTail().getY() + (PORT_SIZE / 2);
		xm = x;

		x = xn * cos - yn * sin + getTail().getX() + (PORT_SIZE / 2);
		yn = xn * sin + yn * cos + getTail().getY() + (PORT_SIZE / 2);
		xn = x;

		int[] xs = { getHead().getX() + (PORT_SIZE / 2), (int) xm, (int) xn };
		int[] ys = { getHead().getY() + (PORT_SIZE / 2), (int) ym, (int) yn };

		g.fillPolygon(xs, ys, 3);
	}

	@Override
	public String toString() {
		return "GeneralizationLine [x=" + x + ", y=" + y + "]";
	}
}
