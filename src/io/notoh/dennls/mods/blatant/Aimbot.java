package io.notoh.dennls.mods.blatant;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by alexa on 5/22/2017.
 */
public class Aimbot extends Module {

    private boolean toggle;
    private int keycode = Keyboard.KEY_I;
    private int delay;

    @Override
    public void setKeyCode(int keyCode) {
        this.keycode = keyCode;
    }

    @Override
    public void onUpdate() {
        if(!this.toggle) return;
        List list = getMC().theWorld.loadedEntityList;
        delay++;
        for(int i = 0; i < list.size(); i++) {
            assert(list.get(i) instanceof Entity): "Non entity in entity list";
            Entity other = (Entity) list.get(i);
            if(other instanceof EntityPlayerSP) continue; //dont want to target the player
            Entity entity = (Entity)list.get(1);

            if(getMC().thePlayer.getDistanceToEntity(entity) > getMC().thePlayer.getDistanceToEntity(other)) {
                entity = other;
            }

            float f = getMC().thePlayer.getDistanceToEntity(entity);
            if(f <= Dennls.getActive().getReach() && getMC().thePlayer.canEntityBeSeen(entity) && delay >= 7) {
                faceEntity(entity);
            }
        }
    }

    public void faceEntity(Entity entity) {
        final float[] rotations = getRotationsNeeded(entity);
        if(rotations != null) {
            getMC().thePlayer.rotationYaw = rotations[0];
            getMC().thePlayer.renderArmPitch = rotations[1] + 1.0f;
        }
    }

    public float[] getRotationsNeeded(Entity entity) {
        if(entity == null) return null;
        final double diffx = entity.posX - getMC().thePlayer.posX;
        final double diffz = entity.posZ - getMC().thePlayer.posZ;
        double diffy;
        if(entity instanceof EntityLivingBase) {
            diffy = entity.posY + entity.getEyeHeight() - (getMC().thePlayer.posY + getMC().thePlayer.getEyeHeight());
        } else {
            diffy = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0 - (getMC().thePlayer.posY +
                    getMC()
                    .thePlayer
                    .getEyeHeight());
        }
        final double dist = MathHelper.sqrt_double(diffx * diffx + diffz * diffz);
        final float yaw = (float) (Math.atan2(diffz,diffx) * 180 / Math.PI) - 90.0f;
        final float pitch = (float) -(Math.atan2(diffy,dist) * 180 / Math.PI);
        return new float[]{getMC().thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - getMC().thePlayer
                .rotationYaw),getMC().thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - getMC()
                .thePlayer.rotationPitch)};
    }


    @Override
    public int getKeyCode() {
        return keycode;
    }

    @Override
    public String getName() {
        return "Aimbot";
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
        return ModCategory.COMBAT;
    }
}
