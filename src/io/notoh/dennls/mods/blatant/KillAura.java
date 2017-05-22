package io.notoh.dennls.mods.blatant;

import io.notoh.dennls.ClientEntry;
import io.notoh.dennls.mods.Module;
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

    @Override
    public void onUpdate() {
        if(!this.toggle) {
            return;
        }
        for(Object object : Minecraft.getMC().theWorld.loadedEntityList) {
            if(object instanceof EntityLivingBase) {
                Entity entity = (EntityLivingBase) object;
                if(entity instanceof EntityPlayerSP) continue;
                if(getMC().thePlayer.getDistanceToEntity(entity) <= ClientEntry.getActive().getReach()) {
                    if(entity.isEntityAlive()) {
                        getMC().playerController.attackEntity(getMC().thePlayer,entity);
                        getMC().thePlayer.swingItem();
                    }
                }
            }
        }
    }

    @Override
    public int getKeyCode() {
        return Keyboard.KEY_C;
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
