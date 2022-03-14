package me.Cutiemango.UMLEditor.mode;

import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.objects.basic.BasicObject;
import me.Cutiemango.UMLEditor.objects.basic.ClassObject;
import me.Cutiemango.UMLEditor.objects.basic.UseCaseObject;

import java.awt.event.MouseEvent;

public class CreateObjectMode extends ToolMode
{
	public CreateObjectMode(BasicObject object) {
		this.object = object;
		this.iconPath = object.getIconPath();
	}

	private final BasicObject object;

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("CreateObjectMode: mousePressed");

		if (object instanceof ClassObject) {
			UMLEditor.addObject(new ClassObject(e.getX(), e.getY()));
			System.out.println("ClassObject");
		} else if (object instanceof UseCaseObject) {
			UMLEditor.addObject(new UseCaseObject(e.getX(), e.getY()));
			System.out.println("UseCaseObject");
		}

		UMLEditor.repaintCanvas();
	}
}
