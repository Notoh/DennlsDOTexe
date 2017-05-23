package io.notoh.dennls.mods.ghost;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.input.Keyboard;

import java.util.Random;

/**
 * Triggerbot hack.
 */
public class Triggerbot extends Module {

    private Timer timer = new Timer();
    private boolean toggle = false;

    private Random random = new Random();
    private int keycode = Keyboard.KEY_T;

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
        return "Triggerbot";
    }
    @Override
    public ModCategory getCategory() {
        return ModCategory.COMBAT;
    }
    @Override
    public void onUpdate() {
        if(!this.toggle) {
            return;
        }
        double delay = random.nextDouble() / 12;
        if(getMC().objectMouseOver != null) {
            if(getMC().objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                Entity entity = getMC().objectMouseOver.entityHit;
                if(!(entity instanceof EntityPlayer) && !(entity instanceof EntityMob) && !(entity instanceof
                        EntityAnimal)) return;
                if(entity.isEntityAlive()) {
                    if(timer.hasReach(delay)) {
                        attackEntity(entity);
                        timer.reset();
                    }
                }
            }
        }
    }

    private void attackEntity(Entity entity) {
        if(!canAttack(getMC().thePlayer,entity)) {
            return;
        }
        getMC().thePlayer.swingItem();
        getMC().playerController.attackEntity(getMC().thePlayer,entity);
    }

    private boolean canAttack(EntityPlayerSP player, Entity target) {
        return getMC().currentScreen == null && !target.isInvisible()&&!player.isUsingItem();
    }

    @Override
    public void toggle() {
        this.toggle = !this.toggle;
        if(this.toggle) {
            super.onEnable();
        } else {
            super.onDisable();
        }
    }

    @Override
    public boolean getToggle() {
        return toggle;
    }

    private class Timer {

        private long lastCheck = getSystemTime();

        boolean hasReach(double seconds) {
            return getTimePassed() >= (seconds * 1000);
        }

        void reset() {
            lastCheck = getSystemTime();
        }

        private long getTimePassed() {
            return getSystemTime() - lastCheck;
        }

        private long getSystemTime() {
            return System.currentTimeMillis();
        }

    }

}
