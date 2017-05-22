package io.notoh.dennls.mods.render;

import io.notoh.dennls.ClientEntry;
import io.notoh.dennls.mods.Module;
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
    private int radius = 4;
    private int xPos;
    private int yPos;
    private int zPos;

    @Override
    public int getKeyCode() {
        return Keyboard.KEY_Z;
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
        if(!toggle || !ClientEntry.nuker.getToggle()) {
            return;
        }
        for(int x = -radius; x < radius; x++) {
            for (int z = -radius; z < radius; z++) {
                for (int y = radius; y > -radius; y--) {
                    this.xPos = (int) getMC().thePlayer.posX + x;
                    this.yPos = (int) getMC().thePlayer.posY + y;
                    this.zPos = (int) getMC().thePlayer.posZ + z;

                    BlockPos pos = new BlockPos(this.xPos, yPos, this.zPos);
                    Block block = getMC().theWorld.getBlockState(pos).getBlock();

                    if (block.getMaterial() == Material.air) {
                        continue;
                    }
                    double renderX = this.xPos - getMC().getRenderManager().renderPosX;
                    double renderY = this.yPos - getMC().getRenderManager().renderPosY;
                    double renderZ = this.zPos - getMC().getRenderManager().renderPosZ;

                    RenderUtils.drawOutlinedBlockESP(renderX,renderY,renderZ,1.0f,1.0f,1.0f,1.0f,1.5f);
                }
            }
        }
    }
}
