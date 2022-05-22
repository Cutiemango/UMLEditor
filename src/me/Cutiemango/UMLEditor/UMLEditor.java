package me.Cutiemango.UMLEditor;

import me.Cutiemango.UMLEditor.components.UMLCanvas;
import me.Cutiemango.UMLEditor.components.UMLMenuBar;
import me.Cutiemango.UMLEditor.components.UMLToolBar;
import me.Cutiemango.UMLEditor.mode.ToolMode;
import me.Cutiemango.UMLEditor.objects.BaseObject;
import me.Cutiemango.UMLEditor.objects.Port;
import me.Cutiemango.UMLEditor.objects.basic.BasicObject;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class UMLEditor
{
	private UMLEditor() {}

	private static final UMLEditor instance = new UMLEditor();

	private final JFrame app = new JFrame();
	private final UMLCanvas canvas = new UMLCanvas();
	private final UMLMenuBar menuBar = new UMLMenuBar();
	private final UMLToolBar toolBar = new UMLToolBar();

	public void start() {
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
		return instance;
	}

	public static UMLCanvas getCanvas() {
		return instance.canvas;
	}

	public static URL getResource(String name) {
		return UMLEditor.class.getResource(name);
	}
}
