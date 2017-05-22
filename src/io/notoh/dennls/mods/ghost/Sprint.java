package io.notoh.dennls.mods.ghost;

import io.notoh.dennls.mods.Module;

/**
 * Created by alexa on 5/22/2017.
 */
public class Sprint extends Module {

    private boolean toggle;

    @Override
    public int getKeyCode() {
        return getMC().gameSettings.keyBindSprint.getKeyCode();
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
