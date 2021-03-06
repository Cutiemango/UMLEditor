package me.Cutiemango.UMLEditor.mode;

import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.objects.basic.BasicObject;
import me.Cutiemango.UMLEditor.objects.basic.ClassObject;
import me.Cutiemango.UMLEditor.objects.basic.UseCaseObject;

import java.awt.event.MouseEvent;

public class CreateObjectMode extends ToolMode
{
	public CreateObjectMode(String iconPath, BasicObject objRef) {
		this.iconPath = iconPath;
		this.objRef = objRef;
	}

	private final BasicObject objRef;

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("CreateObjectMode: mousePressed");

		BasicObject obj = objRef.createObject(e.getX(), e.getY());
		UMLEditor.getCanvas().addObject(obj);
		UMLEditor.getCanvas().repaint();
	}
}
