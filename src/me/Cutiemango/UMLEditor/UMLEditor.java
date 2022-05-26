package me.Cutiemango.UMLEditor;

import me.Cutiemango.UMLEditor.components.UMLCanvas;
import me.Cutiemango.UMLEditor.components.UMLMenuBar;
import me.Cutiemango.UMLEditor.components.UMLToolBar;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.net.URL;

public class UMLEditor
{
	private UMLEditor() {}

	private static UMLEditor instance;

	private final JFrame app = new JFrame();
	private final UMLCanvas canvas = new UMLCanvas();
	private final UMLMenuBar menuBar = new UMLMenuBar();
	private final UMLToolBar toolBar = new UMLToolBar();

	public void launch() {
		app.setLayout(new BorderLayout());
		app.add(menuBar.getMenuBar(), BorderLayout.NORTH);
		app.add(toolBar.getToolBar(), BorderLayout.WEST);
		app.add(canvas.getCanvas(), BorderLayout.CENTER);

		app.setTitle("UML Editor");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(800, 600);
		app.setVisible(true);
	}

	public static UMLEditor getInstance() {
		if (instance == null) {
			instance = new UMLEditor();
		}
		return instance;
	}

	public static UMLCanvas getCanvas() {
		return instance.canvas;
	}

	public static URL getResource(String name) {
		return UMLEditor.class.getResource(name);
	}
}
