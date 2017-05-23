package io.notoh.dennls.mods;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.util.ModCategory;
import io.notoh.dennls.util.ModeUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * Created by alexa on 5/22/2017.
 */
public class ChangeMode extends Module {
    private int keycode = Keyboard.KEY_M;

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
        return "ChangeMode";
    }

    @Override
    public ModCategory getCategory() {
        return ModCategory.OTHER;
    }

    @Override
    public void toggle() {
        Dennls.setActive(ModeUtils.getNext());
        if(Dennls.getActive() != Mode.GHOST) {
            Display.setTitle("DennlsDOTExe (test build 2.0.5)");
        } else {
            Display.setTitle("Minecraft 1.8.8");
        }
    }

    @Override
    public boolean getToggle() {
        return true;
    }
}
