package me.Cutiemango.UMLEditor.mode;

import me.Cutiemango.UMLEditor.objects.line.AssociationLine;
import me.Cutiemango.UMLEditor.objects.line.CompositionLine;
import me.Cutiemango.UMLEditor.objects.line.GeneralizationLine;
import me.Cutiemango.UMLEditor.objects.line.LineObject;

import java.awt.event.MouseEvent;

public class CreateLineMode extends ToolMode
{
	public CreateLineMode(LineObject line) {
		this.line = line;
		this.iconPath = line.getIconPath();
	}

	private final LineObject line;

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("CreateLineMode: mousePressed");

		if (line instanceof AssociationLine) {
			System.out.println("AssociationLine");
		} else if (line instanceof CompositionLine) {
			System.out.println("CompositionLine");
		} else if (line instanceof GeneralizationLine) {
			System.out.println("GeneralizationLine");
		}
	}
}
