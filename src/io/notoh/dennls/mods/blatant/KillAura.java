package io.notoh.dennls.mods.blatant;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.mods.Mode;
import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/22/2017.
 */
public class KillAura extends Module {

    private boolean toggle;
    private int keycode = Keyboard.KEY_Z;

    @Override
    public void onUpdate() {
        if(!this.toggle) {
            return;
        }
        for(Object object : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if(object instanceof EntityLivingBase) {
                Entity entity = (EntityLivingBase) object;
                if(entity instanceof EntityPlayerSP) continue;
                if(getMC().thePlayer.getDistanceToEntity(entity) <= Dennls.getActive().getReach()) {
                    if(entity.isEntityAlive()) {
                        if(Dennls.getActive() != Mode.BLATANT) Dennls.aimbot.faceEntity(entity);
                        getMC().playerController.attackEntity(getMC().thePlayer,entity);
                        getMC().thePlayer.swingItem();
                    }
                }
            }
        }
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
    public ModCategory getCategory() {
        return ModCategory.COMBAT;
    }

    @Override
    public String getName() {
        return "KillAura";
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
