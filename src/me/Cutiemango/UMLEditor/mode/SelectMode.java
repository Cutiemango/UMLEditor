package me.Cutiemango.UMLEditor.mode;

import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.objects.line.LineObject;

import java.awt.event.MouseEvent;

public class SelectMode extends ToolMode
{
	public SelectMode() {
		this.iconPath = "/assets/select.png";
	}

	private int dragOffsetX, dragOffsetY;
	private int dragStartX, dragStartY;

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("SelectMode: mousePressed");

		// find the last (topmost) element under the mouse and select it
		UMLEditor.getObjects().stream().filter(obj -> obj.isWithin(e.getX(), e.getY()))
				 .reduce((first, second) -> second).ifPresentOrElse(obj -> {
					 if (obj instanceof LineObject line) {
						 line.setSelectingTail(line.getTail().isWithin(e.getX(), e.getY()));
						 dragStartX = line.getSelectingPort().getX();
						 dragStartY = line.getSelectingPort().getY();
					 } else {
						 dragStartX = obj.getX();
						 dragStartY = obj.getY();
					 }
					 dragOffsetX = e.getX() - dragStartX;
					 dragOffsetY = e.getY() - dragStartY;
					 UMLEditor.setSelectedObject(obj);
				 }, () -> {
					 UMLEditor.clearSelectedObject();
					 UMLEditor.getCanvas().setShowArea(false);
					 dragStartX = e.getX();
					 dragStartY = e.getY();
				 });

		UMLEditor.getCanvas().repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("SelectMode: mouseReleased");
		UMLEditor.getSelectedObject().filter(obj -> obj instanceof LineObject).ifPresent(obj -> {
			LineObject line = (LineObject) obj;
			UMLEditor.findPort(e.getX(), e.getY())
					 .filter(port -> line.getOtherPort().getConnectedObject() != port.getParent())
					 .ifPresentOrElse(port -> line.getSelectingPort().connect(port), () -> {
						 System.out.println("SelectMode: mouseReleased: no port found");
						 line.getSelectingPort().moveTo(dragStartX, dragStartY);
					 });
		});

		UMLEditor.getCanvas().repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		UMLEditor.getSelectedObject()
				 .ifPresentOrElse(obj -> obj.moveTo(e.getX() - dragOffsetX, e.getY() - dragOffsetY), () -> {
					 int x = Math.min(e.getX(), dragStartX);
					 int y = Math.min(e.getY(), dragStartY);
					 int width = Math.abs(e.getX() - dragStartX);
					 int height = Math.abs(e.getY() - dragStartY);
					 UMLEditor.getCanvas().setShowArea(true);
					 UMLEditor.getCanvas().setSelectedArea(x, y, width, height);
				 });

		UMLEditor.getCanvas().repaint();
	}
}
