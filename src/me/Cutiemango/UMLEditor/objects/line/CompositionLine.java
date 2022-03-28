package me.Cutiemango.UMLEditor.objects.line;

import java.awt.Graphics;

import static me.Cutiemango.UMLEditor.ConfigSettings.ARROW_SIZE;
import static me.Cutiemango.UMLEditor.ConfigSettings.PORT_SIZE;

public class CompositionLine extends LineObject
{
	public CompositionLine(int x, int y, int hx, int hy) {
		super(x, y, hx, hy);
	}

	@Override
	public LineObject createObject(int x, int y, int hx, int hy) {
		return new CompositionLine(x, y, hx, hy);
	}

	@Override
	public void decorateHead(Graphics g) {
		int x1 = getTail().getX() + PORT_SIZE / 2, y1 = getTail().getY() + PORT_SIZE / 2;
		int x2 = getHead().getX() + PORT_SIZE / 2, y2 = getHead().getY() + PORT_SIZE / 2;

		double dx = getHead().getX() - getTail().getX();
		double dy = getHead().getY() - getTail().getY();

		double d = Math.sqrt(dx * dx + dy * dy);
		double xm = d - ARROW_SIZE, xn = xm, x;
		double ym = ARROW_SIZE, yn = -ARROW_SIZE;

		double sin = dy / d, cos = dx / d;
		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		double xq = (ARROW_SIZE * 2 / d) * x1 + ((d - ARROW_SIZE * 2) / d) * x2;
		double yq = (ARROW_SIZE * 2 / d) * y1 + ((d - ARROW_SIZE * 2) / d) * y2;

		int[] xs = { x2, (int) xm, (int) xq, (int) xn };
		int[] ys = { y2, (int) ym, (int) yq, (int) yn };

		g.fillPolygon(xs, ys, 4);
	}

	@Override
	public String toString() {
		return "CompositionLine [x=" + x + ", y=" + y + "]";
	}
}
