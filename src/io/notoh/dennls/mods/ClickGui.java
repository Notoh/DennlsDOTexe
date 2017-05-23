package io.notoh.dennls.mods;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.gui.UIRenderer;
import io.notoh.dennls.util.ModCategory;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/22/2017.
 */
public class ClickGui extends Module {

    private int keycode = Keyboard.KEY_RSHIFT;
    private boolean toggle;

    @Override
    public void setKeyCode(int keyCode) {
        this.keycode = keyCode;
    }

    @Override
    public int getKeyCode() {
        return keycode;
    }

    @Override
    public void onEnable() {
        if(!(getMC().currentScreen instanceof GuiManagerDisplayScreen)) {
            getMC().displayGuiScreen(new GuiManagerDisplayScreen(Dennls.guiManager));
            UIRenderer.renderAndUpdateFrames();
        }
    }

    @Override
    public String getName() {
        return "ClickGUI";
    }

    @Override
    public void toggle() {
        this.toggle = !this.toggle;
        if(this.toggle) {
            onEnable();
        } else {
            onDisable();
        }
    }

    @Override
    public boolean getToggle() {
        return toggle;
    }

    @Override
    public ModCategory getCategory() {
        return ModCategory.OTHER;
    }
}
