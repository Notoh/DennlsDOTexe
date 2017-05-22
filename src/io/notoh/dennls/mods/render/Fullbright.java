package io.notoh.dennls.mods.render;

import io.notoh.dennls.mods.Module;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/21/2017.
 */
public class Fullbright extends Module {

    private float gamma;

    private boolean toggle;

    @Override
    public int getKeyCode() {
        return Keyboard.KEY_B;
    }

    @Override
    public String getName() {
        return "Fullbright";
    }

    @Override
    public void onEnable() {
        this.gamma = 100.0f;
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
    public void onUpdate() {
        getMC().gameSettings.gammaSetting = this.gamma;
    }
}
