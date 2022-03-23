package me.Cutiemango.UMLEditor.mode;

import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.objects.basic.BasicObject;
import me.Cutiemango.UMLEditor.objects.basic.ClassObject;
import me.Cutiemango.UMLEditor.objects.basic.UseCaseObject;

import java.awt.event.MouseEvent;

public class CreateObjectMode extends ToolMode
{
	public CreateObjectMode(BasicObject objRef) {
		this.objRef = objRef;
		this.iconPath = objRef.getIconPath();
	}

	private final BasicObject objRef;

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("CreateObjectMode: mousePressed");

		BasicObject obj = objRef.createObject(e.getX(), e.getY());
		UMLEditor.addObject(obj);
		UMLEditor.getCanvas().repaint();
	}
}
