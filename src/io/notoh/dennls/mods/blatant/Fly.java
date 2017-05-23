package io.notoh.dennls.mods.blatant;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/20/2017.
 */
public class Fly extends Module {

    private boolean toggle = false;
    private int keycode = Keyboard.KEY_F;

    @Override
    public void onUpdate() {
        getMC().thePlayer.capabilities.isFlying = toggle;
    }


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
        return "Flight";
    }

    @Override
    public void onEnable() {
        getMC().thePlayer.capabilities.isFlying = toggle;
    }

    @Override
    public ModCategory getCategory() {
        return ModCategory.MOVEMENT;
    }

    @Override
    public void onDisable() {
        getMC().thePlayer.capabilities.isFlying = false;
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
}
