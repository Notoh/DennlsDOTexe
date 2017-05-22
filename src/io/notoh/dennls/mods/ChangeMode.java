package io.notoh.dennls.mods;

import io.notoh.dennls.ClientEntry;
import io.notoh.dennls.util.ModeUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * Created by alexa on 5/22/2017.
 */
public class ChangeMode extends Module {
    @Override
    public int getKeyCode() {
        return Keyboard.KEY_M;
    }

    @Override
    public String getName() {
        return "Change Mode";
    }

    @Override
    public void toggle() {
        ClientEntry.setActive(ModeUtils.getNext());
        if(ClientEntry.getActive() != Mode.GHOST) {
            ClientEntry.getClientGui().renderScreen();
            Display.setTitle("DennlsDOTExe (test build 1.0.1)");
        } else {
            Display.setTitle("Minecraft 1.8");
        }
    }

    @Override
    public boolean getToggle() {
        return false;
    }
}
