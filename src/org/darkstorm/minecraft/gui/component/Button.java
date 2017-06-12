package org.darkstorm.minecraft.gui.component;

import org.darkstorm.minecraft.gui.listener.ButtonListener;

public interface Button extends Component, TextComponent {
	void press();

	void addButtonListener(ButtonListener listener);

	void removeButtonListener(ButtonListener listener);

	ButtonGroup getGroup();

	void setGroup(ButtonGroup group);
}
