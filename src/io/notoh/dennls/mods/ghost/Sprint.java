package io.notoh.dennls.mods.ghost;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;

/**
 * Created by alexa on 5/22/2017.
 */
public class Sprint extends Module {

    private boolean toggle;
    private int keycode = getMC().gameSettings.keyBindSprint.getKeyCode();

    @Override
    public void setKeyCode(int keyCode) {
        getMC().gameSettings.keyBindSprint.setKeyCode(keycode);
        this.keycode = getMC().gameSettings.keyBindSprint.getKeyCode();
    }
    @Override
    public int getKeyCode() {
        return keycode;
    }

    @Override
    public String getName() {
        return "Sprint";
    }

    @Override
    public void onEnable() {
        getMC().gameSettings.keyBindSprint.pressed = true;
    }

    @Override
    public void onDisable() {
        getMC().gameSettings.keyBindSprint.pressed = false;
    }

    @Override
    public void onUpdate() {
        if(!this.toggle) return;
        getMC().gameSettings.keyBindSprint.pressed = true;
    }
    @Override
    public ModCategory getCategory() {
        return ModCategory.MOVEMENT;
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
