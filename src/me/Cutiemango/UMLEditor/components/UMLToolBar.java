package me.Cutiemango.UMLEditor.components;

import me.Cutiemango.UMLEditor.UMLEditor;
import me.Cutiemango.UMLEditor.mode.CreateLineMode;
import me.Cutiemango.UMLEditor.mode.CreateObjectMode;
import me.Cutiemango.UMLEditor.mode.SelectMode;
import me.Cutiemango.UMLEditor.mode.ToolMode;
import me.Cutiemango.UMLEditor.objects.basic.ClassObject;
import me.Cutiemango.UMLEditor.objects.basic.UseCaseObject;
import me.Cutiemango.UMLEditor.objects.line.AssociationLine;
import me.Cutiemango.UMLEditor.objects.line.CompositionLine;
import me.Cutiemango.UMLEditor.objects.line.GeneralizationLine;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import static me.Cutiemango.UMLEditor.ConfigSettings.BUTTON_SELECTED_COLOR;

public class UMLToolBar
{
	public UMLToolBar() {
		toolbar.setLayout(new GridLayout(6, 1, 2, 2));
		toolbar.setBackground(new Color(85, 85, 85));
		toolbar.setBorderPainted(false);
		toolbar.setFloatable(false);

		toolbar.add(createButton(new SelectMode()));

		toolbar.add(createButton(new CreateLineMode(new AssociationLine(0, 0, 0, 0))));
		toolbar.add(createButton(new CreateLineMode(new GeneralizationLine(0, 0, 0, 0))));
		toolbar.add(createButton(new CreateLineMode(new CompositionLine(0, 0, 0, 0))));

		toolbar.add(createButton(new CreateObjectMode(new ClassObject(0, 0))));
		toolbar.add(createButton(new CreateObjectMode(new UseCaseObject(0, 0))));
	}

	private final JToolBar toolbar = new JToolBar();
	private JButton selectedButton = null;

	public JToolBar getToolBar() {
		return toolbar;
	}

	private JButton createButton(ToolMode mode) {
		JButton button = new JButton();
		button.setIcon(new ImageIcon(UMLEditor.getResource(mode.getIconPath())));
		button.setBorderPainted(false);
		button.setBackground(new Color(0, 0, 0));
		button.setRolloverEnabled(true);
		button.setFocusable(false);
		button.addActionListener(e -> {
			if (selectedButton != null) {
				// reset button color
				selectedButton.setBackground(new Color(0, 0, 0));
			}
			selectedButton = button;
			selectedButton.setBackground(BUTTON_SELECTED_COLOR);
			UMLEditor.switchToMode(mode);
			UMLEditor.repaintCanvas();
		});
		return button;
	}
}
