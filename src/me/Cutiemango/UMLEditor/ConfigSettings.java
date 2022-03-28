package me.Cutiemango.UMLEditor;

import java.awt.Color;
import java.awt.Font;

public class ConfigSettings
{
	public static final String DEFAULT_OBJECT_NAME = "Object";
	public static final Font DEFAULT_FONT = new Font("Consolas", Font.PLAIN, 14);

	public static final int PORT_SIZE = 10;

	public static final int CLASS_OBJECT_HEIGHT = 120;
	public static final int CLASS_OBJECT_WIDTH = 100;

	public static final int USECASE_OBJECT_HEIGHT = 90;
	public static final int USECASE_OBJECT_WIDTH = 120;

	public static final Color CANVAS_BACKGROUND_COLOR = new Color(0x202020);

	public static final Color DEFAULT_OBJECT_COLOR = new Color(0xffffff);
	public static final Color DEFAULT_BUTTON_COLOR = new Color(0x000000);
	public static final Color BUTTON_SELECTED_COLOR = new Color(0x3772cd);
	public static final Color OBJECT_SELECTED_COLOR = new Color(0x3772cd);

	public static final Color GROUP_BORDER_COLOR = new Color(0x5291f1);
	public static final Color GROUP_SELECTED_COLOR = new Color(145 / 255f, 185 / 255f, 241 / 255f, 0.3f);

	public static final int SELECTED_AREA_THRESHOLD = 10;
}
