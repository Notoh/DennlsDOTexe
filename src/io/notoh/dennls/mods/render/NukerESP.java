package io.notoh.dennls.mods.render;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import io.notoh.dennls.util.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/21/2017.
 */
public class NukerESP extends Module {

    private boolean toggle;
    private int keycode = Keyboard.KEY_V;

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
        return ModCategory.RENDER;
    }

    @Override
    public String getName() {
        return "NukerESP";
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
        if(!toggle || !Dennls.nuker.getToggle()) {
            return;
        }
        int radius = 4;
        for(int x = -radius; x < radius; x++) {
            for (int z = -radius; z < radius; z++) {
                for (int y = radius; y > -radius; y--) {
                    int xPos = (int) getMC().thePlayer.posX + x;
                    int yPos = (int) getMC().thePlayer.posY + y;
                    int zPos = (int) getMC().thePlayer.posZ + z;

                    BlockPos pos = new BlockPos(xPos, yPos, zPos);
                    Block block = getMC().theWorld.getBlockState(pos).getBlock();

                    if (block.getMaterial() == Material.air) {
                        continue;
                    }
                    double renderX = xPos - getMC().getRenderManager().renderPosX;
                    double renderY = yPos - getMC().getRenderManager().renderPosY;
                    double renderZ = zPos - getMC().getRenderManager().renderPosZ;

                    // RenderUtils.drawOutlinedBlockESP(renderX,renderY,renderZ,1.0f,1.0f,1.0f,1.0f,1.5f);
                    RenderUtils.drawBlockESP(renderX,renderY,renderZ,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,
                            1.0f);
                }
            }
        }
    }
}
