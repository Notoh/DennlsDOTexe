package io.notoh.dennls.mods.ghost;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/23/2017.
 */
public class SmoothAim extends Module {

    private int keycode = Keyboard.KEY_K;
    private boolean toggle;
    private float min = 1.0e-1f;
    private float max = 10.0f;

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
        return "SmoothAim";
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

    private boolean canBeHit(Entity entity) {
        if(!(entity instanceof EntityLivingBase)) return false;
        float[] yawAndPitch = Dennls.aimbot.getRotationsNeeded(entity);
        float yaw = yawAndPitch[0];
        float pitch = yawAndPitch[1];

        if(getDistanceBetweenAngles(getMC().thePlayer.rotationYaw, yaw) < Dennls.getActive().getFov()) {
            if(getDistanceBetweenAngles(getMC().thePlayer.rotationPitch,pitch) < Dennls.getActive().getFov()) {
                return true;
            }
        }
        return false;
    }

    private float getDistanceBetweenAngles(float angle1, float angle2) {
        return Math.abs(angle1 % 360.0f - angle2 % 360.0f) % 360.0f;
    }

    private void faceEntity(Entity entity, float yaw, float pitch) {
        if(entity == null) return;
        final double diffx = entity.posX - getMC().thePlayer.posX;
        final double diffz = entity.posZ - getMC().thePlayer.posZ;
        final float newyaw = (float) (Math.atan2(diffz,diffx) * 180 / Math.PI) - 90.0f;
        getMC().thePlayer.rotationYaw = updateRotation(getMC().thePlayer.rotationYaw,newyaw,yaw);
    }

    private float updateRotation(float currentyaw,float needed, float change) {
        float angle = MathHelper.wrapAngleTo180_float(needed - currentyaw);
        if(angle > change) {
            angle = change;
        }
        if(angle < -change) {
            angle = change;
        }
        return currentyaw + angle;
    }

    @Override
    public void onUpdate() {
        if(!this.toggle) {
            return;
        }
        for(Entity entity : getMC().theWorld.loadedEntityList) {
            if(!(getMC().currentScreen instanceof GuiContainer) && !(getMC().currentScreen instanceof GuiChat)
                    && !(getMC().currentScreen instanceof GuiIngameMenu) && !(entity instanceof EntityPlayerSP)
                    && !(getMC().thePlayer.isDead) && (getMC().thePlayer.canEntityBeSeen(entity)) && (getMC().thePlayer
                    .getDistanceToEntity(entity) <= Dennls.getActive().getReach()) && (canBeHit(entity))) {
                faceEntity(entity,max/10,min);
                return;
            }
        }

    }


    @Override
    public boolean getToggle() {
        return toggle;
    }

    @Override
    public ModCategory getCategory() {
        return ModCategory.COMBAT;
    }
}
