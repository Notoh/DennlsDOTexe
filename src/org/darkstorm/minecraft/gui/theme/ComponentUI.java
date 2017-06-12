package org.darkstorm.minecraft.gui.theme;

import java.awt.*;

import org.darkstorm.minecraft.gui.component.Component;
import org.darkstorm.minecraft.gui.component.Container;

public interface ComponentUI {
	void render(Component component);

	Rectangle getChildRenderArea(Container container);

	Dimension getDefaultSize(Component component);

	Color getDefaultBackgroundColor(Component component);

	Color getDefaultForegroundColor(Component component);

	Rectangle[] getInteractableRegions(Component component);

	void handleInteraction(Component component, Point location, int button);

	void handleUpdate(Component component);
}
