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
import java.io.InputStream;
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

	private final List<BaseObject> objects = new ArrayList<>();
	private BaseObject selectedObject = null;
	private ToolMode currentMode = null;

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

	// find the nearest port location with respect to (x, y)
	public static Optional<Port> findPort(int x, int y) {
		return instance.objects.stream().filter(o -> o instanceof BasicObject && o.isWithin(x, y))
				.map(o -> ((BasicObject) o).getPorts()).flatMap(Collection::stream)
				.min(Comparator.comparingInt(port -> (port.getX() - x) * (port.getX() - x) + (port.getY() - y) * (port.getY() - y)));
	}

	public static ToolMode getCurrentMode() {
		return instance.currentMode;
	}

	public static void switchToMode(ToolMode newMode) {
		instance.canvas.switchMode(instance.currentMode, newMode);
		instance.currentMode = newMode;
	}

	public static URL getResource(String name) {
		return UMLEditor.class.getResource(name);
	}
}
