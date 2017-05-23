package io.notoh.dennls.mods.blatant;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import net.minecraft.util.MovementInput;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/22/2017.
 */
public class BypassSpeed extends Module {

    private boolean toggle;
    private int keycode = Keyboard.KEY_U;

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
            if((getMC().thePlayer.moveForward != 0 || getMC().thePlayer.moveStrafing != 0) && getMC().thePlayer
                    .onGround && !getMC().thePlayer.isSneaking()) {
                getMC().thePlayer.motionY = 0.4d;
                boolean hack = getMC().thePlayer.ticksExisted  % 2 == 0;
                setSpeed(hack ? 0.1D : 1.6d); //might need to be 0.06
            }
    }

    private void setSpeed(double speed) {
        final MovementInput input = getMC().thePlayer.movementInput;
        float forward = input.moveForward;
        float strafe = input.moveStrafe;
        float yaw = getMC().thePlayer.rotationYaw;
        if(forward == 0.0f && strafe == 0.0f) {
            getMC().thePlayer.motionX = 0;
            getMC().thePlayer.motionZ = 0;
        } else if(forward != 0.0f) {
            if(strafe >= 1.0f) {
                yaw += ((forward > 0.0f) ? -45 : 45);
                strafe = 0.0f;
            } else if(strafe <= -1.0f) {
                yaw += ((forward > 0.0f) ? 45 : -45);
                strafe = 0.0f;
            }
            if(forward > 0.0f) {
                forward = 1.0f;
            } else if(forward < 0.0f) {
                forward = -1f;
            }
        }
        final double mx = Math.cos(Math.toRadians(yaw + 90.0f));
        final double mz = Math.sin(Math.toRadians(yaw + 90.0f));
        getMC().thePlayer.motionX = forward * speed * mx + strafe * speed * mz;
        getMC().thePlayer.motionZ = forward * speed * mz - strafe * speed * mx;
        if(forward == 0.0f && strafe == 0.0f) {
            getMC().thePlayer.motionX = 0.0;
            getMC().thePlayer.motionZ = 0.0;
        }
    }

    @Override
    public String getName() {
        return "BypassSpeed";
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
