package me.Cutiemango.UMLEditor.mode;

import java.awt.event.MouseEvent;

public class SelectMode extends ToolMode
{
	public SelectMode() {
		this.iconPath = "/assets/select.png";
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("SelectMode: mousePressed");
	}
}
