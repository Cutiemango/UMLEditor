package me.Cutiemango.UMLEditor.mode;

import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.objects.line.LineObject;

import java.awt.event.MouseEvent;

public class CreateLineMode extends ToolMode
{
	public CreateLineMode(LineObject objRef) {
		this.objRef = objRef;
		this.iconPath = objRef.getIconPath();
	}

	private final LineObject objRef;

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("CreateLineMode: mousePressed");
		UMLEditor.findPort(e.getX(), e.getY()).ifPresent(port -> {
			LineObject line = objRef.createObject(port.getX(), port.getY(), e.getX(), e.getY());
			line.getTail().connect(port);
			UMLEditor.addObject(line);
			UMLEditor.setSelectedObject(line);
		});
		UMLEditor.getCanvas().repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("CreateLineMode: mouseReleased");
		UMLEditor.getSelectedObject().filter(o -> o instanceof LineObject).ifPresent(o -> {
			LineObject line = (LineObject) o;
			UMLEditor.findPort(e.getX(), e.getY())
					 .filter(port -> line.getTail().getConnectedObject() != port.getParent())
					 .ifPresentOrElse(port -> line.getHead().connect(port), () -> {
						 UMLEditor.removeObject(line);
						 UMLEditor.clearSelectedObject();
					 });
		});
		UMLEditor.getCanvas().repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		UMLEditor.getSelectedObject().filter(o -> o instanceof LineObject).ifPresent(o -> {
			LineObject line = (LineObject) o;
			line.getHead().moveTo(e.getX(), e.getY());
		});
		UMLEditor.getCanvas().repaint();
	}
}
