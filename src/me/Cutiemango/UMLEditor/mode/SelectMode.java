package me.Cutiemango.UMLEditor.mode;

import me.Cutiemango.UMLEditor.UMLEditor;

import java.awt.event.MouseEvent;

public class SelectMode extends ToolMode
{
	public SelectMode() {
		this.iconPath = "/assets/select.png";
	}

	private int dragOffsetX, dragOffsetY;

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("SelectMode: mousePressed");

		// find the last (topmost) element under the mouse and select it
		UMLEditor.getObjects().stream().filter(obj -> obj.isWithin(e.getX(), e.getY())).reduce((first, second) -> second)
				.ifPresentOrElse(obj -> {
					UMLEditor.setSelectedObject(obj);
					dragOffsetX = e.getX() - obj.getX();
					dragOffsetY = e.getY() - obj.getY();
				}, UMLEditor::clearSelectedObject);

		UMLEditor.repaintCanvas();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("SelectMode: mouseReleased");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("SelectMode: mouseDragged");
		UMLEditor.getSelectedObject().ifPresent(obj -> obj.moveTo(e.getX() - dragOffsetX, e.getY() - dragOffsetY));
		UMLEditor.repaintCanvas();
	}
}
