package me.Cutiemango.UMLEditor.mode;

import me.Cutiemango.UMLEditor.Pair;
import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.objects.BaseObject;
import me.Cutiemango.UMLEditor.objects.Port;
import me.Cutiemango.UMLEditor.objects.basic.BasicObject;
import me.Cutiemango.UMLEditor.objects.line.AssociationLine;
import me.Cutiemango.UMLEditor.objects.line.CompositionLine;
import me.Cutiemango.UMLEditor.objects.line.GeneralizationLine;
import me.Cutiemango.UMLEditor.objects.line.LineObject;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class CreateLineMode extends ToolMode
{
	public CreateLineMode(LineObject obj) {
		this.obj = obj;
		this.iconPath = obj.getIconPath();
	}

	private final LineObject obj;

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("CreateLineMode: mousePressed");
		UMLEditor.findPort(e.getX(), e.getY()).ifPresent(port -> {
			LineObject line;
			if (obj instanceof AssociationLine) {
				line = new AssociationLine(port.getX(), port.getY(), e.getX(), e.getY());
				System.out.println("AssociationLine");
			} else if (obj instanceof CompositionLine) {
				line = new CompositionLine(port.getX(), port.getY(), e.getX(), e.getY());
				System.out.println("CompositionLine");
			} else if (obj instanceof GeneralizationLine) {
				line = new GeneralizationLine(port.getX(), port.getY(), e.getX(), e.getY());
				System.out.println("GeneralizationLine");
			} else {
				throw new IllegalArgumentException("Unknown LineObject type");
			}

			line.getTail().connect(port);
			UMLEditor.addObject(line);
			UMLEditor.setSelectedObject(line);
		});
		UMLEditor.repaintCanvas();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("CreateLineMode: mouseReleased");
		UMLEditor.getSelectedObject().filter(o -> o instanceof LineObject).ifPresent(o -> {
			LineObject line = (LineObject) o;
			UMLEditor.findPort(e.getX(), e.getY()).filter(port -> line.getTail().getConnectedObject() != port.getParent())
					.ifPresentOrElse(port -> line.getHead().connect(port), () -> {
						UMLEditor.removeObject(line);
						UMLEditor.clearSelectedObject();
					});
		});
		UMLEditor.repaintCanvas();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		UMLEditor.getSelectedObject().filter(o -> o instanceof LineObject).ifPresent(o -> {
			LineObject line = (LineObject) o;
			line.getHead().moveTo(e.getX(), e.getY());
		});
		UMLEditor.repaintCanvas();
	}
}
