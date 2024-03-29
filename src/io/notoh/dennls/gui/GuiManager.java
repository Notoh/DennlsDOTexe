/*
 * Copyright (c) 2013, DarkStorm (darkstorm@evilminecraft.net)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: 
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package io.notoh.dennls.gui;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.mods.ChangeMode;
import io.notoh.dennls.mods.ClickGui;
import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import net.minecraft.client.Minecraft;
import org.darkstorm.minecraft.gui.AbstractGuiManager;
import org.darkstorm.minecraft.gui.component.Button;
import org.darkstorm.minecraft.gui.component.Component;
import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.component.basic.BasicButton;
import org.darkstorm.minecraft.gui.component.basic.BasicFrame;
import org.darkstorm.minecraft.gui.component.basic.BasicLabel;
import org.darkstorm.minecraft.gui.layout.GridLayoutManager;
import org.darkstorm.minecraft.gui.theme.Theme;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Minecraft GUI API
 * 
 * This class is not actually intended for use; rather, you should use this as a
 * template for your actual GuiManager, as the creation of frames is highly
 * implementation-specific.
 * 
 * @author DarkStorm (darkstorm@evilminecraft.net)
 */
public final class GuiManager extends AbstractGuiManager {
	private class ModuleFrame extends BasicFrame {

		private ModuleFrame(String title) {
			super(title);
		}
	}


	@Override
	public void setup() {
        final Map<ModCategory,ModuleFrame> frames = new HashMap<>();

        for(Module module : Dennls.getClient().getMods()) {
            if(module instanceof ClickGui) continue;
            ModuleFrame frame = frames.get(module.getCategory());
            if(frame == null) {
                String name = module.getCategory().name().toLowerCase();
                name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                frame = new ModuleFrame(name);
                frame.setTheme(new SimpleTheme());
                frame.setLayoutManager(new GridLayoutManager(2,0));
                frame.setVisible(true);
                frame.setClosable(false);
                frame.setMinimized(true);

                addFrame(frame);
                frames.put(module.getCategory(),frame);
            }
            frame.add(new BasicLabel(module.getName()));
            final Module updated = module;
            Button button = new BasicButton(module.getToggle() ? module instanceof ChangeMode ? "Change" :
                    "Disable" : "Enable") {
                @Override
                public void update() {
                    setText(updated.getToggle()? updated instanceof ChangeMode ?
							"Change" :
                            "Disable" : "Enable");
                }
            };
            button.addButtonListener(b -> {
                updated.toggle();
                b.setText(updated.getToggle()? updated instanceof ChangeMode ?
                        "Change" :
                        "Disable" : "Enable");
            });
            frame.add(button, GridLayoutManager.HorizontalGridConstraint.RIGHT);
        }

		// Optional equal sizing and auto-positioning
		resizeComponents();
		Minecraft minecraft = Minecraft.getMinecraft();
		Dimension maxSize = recalculateSizes();
		int offsetX = 5, offsetY = 5;
		int scale = minecraft.gameSettings.guiScale;
		if(scale == 0)
			scale = 1000;
		int scaleFactor = 0;
		while(scaleFactor < scale && minecraft.displayWidth / (scaleFactor + 1) >= 320 && minecraft.displayHeight / (scaleFactor + 1) >= 240)
			scaleFactor++;
		for(Frame frame : getFrames()) {
			frame.setX(offsetX);
			frame.setY(offsetY);
			offsetX += maxSize.width + 5;
			if(offsetX + maxSize.width + 5 > minecraft.displayWidth / scaleFactor) {
				offsetX = 5;
				offsetY += maxSize.height + 5;
			}
		}
	}


	@Override
	protected void resizeComponents() {
		Theme theme = getTheme();
		Frame[] frames = getFrames();
		Button enable = new BasicButton("Enable");
		Button disable = new BasicButton("Disable");
		Dimension enableSize = theme.getUIForComponent(enable).getDefaultSize(enable);
		Dimension disableSize = theme.getUIForComponent(disable).getDefaultSize(disable);
		int buttonWidth = Math.max(enableSize.width, disableSize.width);
		int buttonHeight = Math.max(enableSize.height, disableSize.height);
		for(Frame frame : frames) {
			if(frame instanceof ModuleFrame) {
				for(Component component : frame.getChildren()) {
					if(component instanceof Button) {
						component.setWidth(buttonWidth);
						component.setHeight(buttonHeight);
					}
				}
			}
		}
		recalculateSizes();
	}

	private Dimension recalculateSizes() {
		Frame[] frames = getFrames();
		int maxWidth = 0, maxHeight = 0;
		for(Frame frame : frames) {
			Dimension defaultDimension = frame.getTheme().getUIForComponent(frame).getDefaultSize(frame);
			maxWidth = Math.max(maxWidth, defaultDimension.width);
			frame.setHeight(defaultDimension.height);
			if(frame.isMinimized()) {
				for(Rectangle area : frame.getTheme().getUIForComponent(frame).getInteractableRegions(frame))
					maxHeight = Math.max(maxHeight, area.height);
			} else
				maxHeight = Math.max(maxHeight, defaultDimension.height);
		}
		for(Frame frame : frames) {
			frame.setWidth(maxWidth);
			frame.layoutChildren();
		}
		return new Dimension(maxWidth, maxHeight);
	}
}
