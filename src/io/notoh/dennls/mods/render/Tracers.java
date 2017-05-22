package io.notoh.dennls.mods.render;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.CanRender;
import io.notoh.dennls.util.RenderUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;

/**
 * Tracers hack
 * @author Notoh
 */
public class Tracers extends Module implements CanRender {

    private boolean toggle;

    @Override
    public int getKeyCode() {
        return Keyboard.KEY_Y;
    }

    @Override
    public String getName() {
        return "Tracers";
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
    public void onRender() {
        if(!this.toggle) {
            return;
        }
        for(Object object : getMC().theWorld.loadedEntityList) {
            if(!(object instanceof EntityLivingBase)) {
                continue;
            }
            EntityLivingBase entity = (EntityLivingBase) object;
            if(entity instanceof EntityPlayer) {
                if(!entity.equals(getMC().thePlayer)) {
                    RenderUtils.renderPlayer(entity,this);
                }
                continue;
            }
            if(entity instanceof EntityMob) {
                RenderUtils.renderMob(entity,this);
                continue;
            }
            if(entity instanceof EntityAnimal) {
                RenderUtils.renderAnimal(entity,this);
                continue;
            }
            RenderUtils.renderPassive(entity,this);
        }
    }



    public void render(float red, float green, float blue, double x, double y, double z) {
        RenderUtils.drawTracerLine(x,y,z,red,green,blue,0.45f,1.0f);
    }

}
