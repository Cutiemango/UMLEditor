package me.Cutiemango.UMLEditor;

import me.Cutiemango.UMLEditor.components.UMLCanvas;
import me.Cutiemango.UMLEditor.components.UMLMenuBar;
import me.Cutiemango.UMLEditor.components.UMLToolBar;
import me.Cutiemango.UMLEditor.mode.ToolMode;
import me.Cutiemango.UMLEditor.objects.BaseObject;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
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

	private final List<BaseObject> objects = new ArrayList<>();
	private BaseObject selectedObject = null;
	private ToolMode currentMode = null;

	public void run() {
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

	public static List<BaseObject> getObjects() {
		return instance.objects;
	}

	public static void addObject(BaseObject object) {
		instance.objects.add(object);
	}

	public static void removeObject(BaseObject object) {
		instance.objects.remove(object);
	}

	public static Optional<BaseObject> getSelectedObject() {
		return Optional.ofNullable(instance.selectedObject);
	}

	public static void setSelectedObject(BaseObject object) {
		clearSelectedObject();
		System.out.println("Selected object: " + object);
		if (object != null) {
			object.setSelected(true);
			instance.selectedObject = object;
		}
	}

	public static void clearSelectedObject() {
		if (instance.selectedObject != null) {
			instance.selectedObject.setSelected(false);
		}
		instance.selectedObject = null;
	}

	public static ToolMode getCurrentMode() {
		return instance.currentMode;
	}

	public static void switchToMode(ToolMode newMode) {
		instance.canvas.switchMode(instance.currentMode, newMode);
		instance.currentMode = newMode;
	}

	public static void repaintCanvas() {
		instance.canvas.repaint();
	}

	public static URL getResource(String name) {
		return UMLEditor.class.getResource(name);
	}

	public static InputStream getResourceAsStream(String name) {
		return UMLEditor.class.getResourceAsStream(name);
	}
}
