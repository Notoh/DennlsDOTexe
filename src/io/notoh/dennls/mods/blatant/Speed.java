package io.notoh.dennls.mods.blatant;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/22/2017.
 */
public class Speed extends Module {

    private boolean toggle;
    private int keycode = Keyboard.KEY_R;

    @Override
    public void setKeyCode(int keyCode) {
        this.keycode = keyCode;
    }

    @Override
    public int getKeyCode() {
        return keycode;
    }


    @Override
    public void onUpdate() {
        if(!this.toggle) return;
        if((getMC().thePlayer.moveForward != 0 || getMC().thePlayer.moveStrafing != 0)
                && !getMC().thePlayer.isSneaking() && getMC().thePlayer.onGround) {
            getMC().thePlayer.jump();
            getMC().thePlayer.motionX *= 1.4;
            getMC().thePlayer.motionY *= 0.4;
            getMC().thePlayer.motionZ *= 1.4;
        }

    }

    @Override
    public String getName() {
        return "Speed";
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
        return ModCategory.MOVEMENT;
    }

}
