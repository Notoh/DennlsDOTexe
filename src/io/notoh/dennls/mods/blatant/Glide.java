package io.notoh.dennls.mods.blatant;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/22/2017.
 */
public class Glide extends Module {

    private int keycode = Keyboard.KEY_G;
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
    public String getName() {
        return "Glide";
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

    @Override
    public void onEnable() {
        double oldY = getMC().thePlayer.motionY;
        float oldJ = getMC().thePlayer.jumpMovementFactor;
        if((getMC().thePlayer.motionY < 0.0d) && (getMC().thePlayer.isAirBorne) && (!getMC().thePlayer.isInWater
                    ()) && (!getMC().thePlayer.isOnLadder())) {
                getMC().thePlayer.motionY = -0.1d;
                getMC().thePlayer.jumpMovementFactor *= 1.12337f;
        }
    }



    @Override
    public void onUpdate() {
        double oldY = getMC().thePlayer.motionY;
        float oldJ = getMC().thePlayer.jumpMovementFactor;
        if(this.toggle) {
            if((getMC().thePlayer.motionY < 0.0d) && (getMC().thePlayer.isAirBorne) && (!getMC().thePlayer.isInWater
                    ()) && (!getMC().thePlayer.isOnLadder())) {
                getMC().thePlayer.motionY = -.125d;
                getMC().thePlayer.jumpMovementFactor *= 1.12337f;
            }
        } else {
            getMC().thePlayer.motionY = oldY;
            getMC().thePlayer.jumpMovementFactor = oldJ;
        }
    }
}
