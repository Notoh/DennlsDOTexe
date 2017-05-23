package io.notoh.dennls.mods.render;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/22/2017.
 */
public class Xray extends Module {

    private boolean toggle;
    private int keycode = Keyboard.KEY_X;

    @Override
    public void setKeyCode(int keyCode) {
        this.keycode = keyCode;
    }

    @Override
    public int getKeyCode() {
        return keycode;
    }

    @Override
    public String getName() {
        return "XRay";
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
    public void onEnable() {
        getMC().renderGlobal.loadRenderers();
    }

    @Override
    public void onDisable() {
        getMC().renderGlobal.loadRenderers();
    }

    @Override
    public boolean getToggle() {
        return toggle;
    }

    @Override
    public ModCategory getCategory() {
        return ModCategory.RENDER;
    }
}
