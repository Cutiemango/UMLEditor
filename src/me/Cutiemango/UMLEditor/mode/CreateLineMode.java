package me.Cutiemango.UMLEditor.mode;

import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.objects.line.LineObject;

import java.awt.event.MouseEvent;

public class CreateLineMode extends ToolMode
{
	public CreateLineMode(String iconPath, LineObject objRef) {
		this.iconPath = iconPath;
		this.objRef = objRef;
	}

	private final LineObject objRef;

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("CreateLineMode: mousePressed");
		UMLEditor.getCanvas().findPort(e.getX(), e.getY()).ifPresent(port -> {
			LineObject line = objRef.createObject(port.getX(), port.getY(), e.getX(), e.getY());
			line.getTail().connect(port);
			UMLEditor.getCanvas().addObject(line);
			UMLEditor.getCanvas().setSelectedObject(line);
		});
		UMLEditor.getCanvas().repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("CreateLineMode: mouseReleased");
		UMLEditor.getCanvas().getSelectedObject().filter(o -> o instanceof LineObject).ifPresent(o -> {
			LineObject line = (LineObject) o;
			UMLEditor.getCanvas().findPort(e.getX(), e.getY())
					 .filter(port -> line.getTail().getConnectedObject() != port.getParent())
					 .ifPresentOrElse(port -> line.getHead().connect(port), () -> {
						 UMLEditor.getCanvas().removeObject(line);
						 UMLEditor.getCanvas().resetSelection();
					 });
		});
		UMLEditor.getCanvas().repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		UMLEditor.getCanvas().getSelectedObject().filter(o -> o instanceof LineObject).ifPresent(o -> {
			LineObject line = (LineObject) o;
			line.getHead().moveTo(e.getX(), e.getY());
		});
		UMLEditor.getCanvas().repaint();
	}
}
