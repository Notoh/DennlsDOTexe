package io.notoh.dennls.mods.blatant;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/22/2017.
 */
public class Waterwalk extends Module {

    private boolean toggle;
    private int keyCode = Keyboard.KEY_SEMICOLON;


    @Override
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public int getKeyCode() {
        return keyCode;
    }

    @Override
    public String getName() {
        return "Waterwalk";
    }

    @Override
    public void toggle() {
        this.toggle = !this.toggle;
    }

    @Override
    public boolean getToggle() {
        return toggle;
    }

    @Override
    public ModCategory getCategory() {
        return ModCategory.PLAYER;
    }
}
