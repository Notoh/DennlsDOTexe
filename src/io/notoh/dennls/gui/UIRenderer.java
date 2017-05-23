package io.notoh.dennls.gui;

import io.notoh.dennls.Dennls;
import net.minecraft.client.Minecraft;
import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;

/**
 * Created by alexa on 5/22/2017.
 */
public final class UIRenderer {

    public static void renderAndUpdateFrames() {
        for(Frame frame : Dennls.guiManager.getFrames()) {
            frame.update();
        }
        for(Frame frame : Dennls.guiManager.getFrames()) {
            if(frame.isPinned() || Minecraft.getMinecraft().currentScreen instanceof GuiManagerDisplayScreen) {
                frame.render();
            }
        }
    }

}
