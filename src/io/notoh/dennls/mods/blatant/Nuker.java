package io.notoh.dennls.mods.blatant;

import io.notoh.dennls.mods.Module;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/20/2017.
 */
public class Nuker extends Module {

    private int xPos;
    private int yPos;
    private int zPos;
    private boolean toggle;
    private int radius = 4;


    @Override
    public int getKeyCode() {
        return Keyboard.KEY_X;
    }

    @Override
    public void onUpdate() {
        if(!toggle) {
            return;
        }
        for(int x = -radius; x < radius; x++) {
            for(int z = -radius; z < radius; z++) {
                for(int y = radius; y > -radius; y--) {
                    this.xPos = (int) getMC().thePlayer.posX + x;
                    this.yPos = (int) getMC().thePlayer.posY + y;
                    this.zPos = (int) getMC().thePlayer.posZ + z;

                    BlockPos pos = new BlockPos(this.xPos,yPos,this.zPos);
                    Block block = getMC().theWorld.getBlockState(pos).getBlock();

                    if(block.getMaterial() == Material.air) {
                        continue;
                    }
                    getMC().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging
                            (C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, EnumFacing.UP));
                    getMC().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging
                            (C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, EnumFacing.UP));
                }
            }
        }
    }


    @Override
    public String getName() {
        return "Nuker";
    }


    @Override
    public void toggle() {
        this.toggle = !this.toggle;
        if(toggle) {
            super.onEnable();
        } else {
            super.onDisable();
        }
    }

    @Override
    public boolean getToggle() {
        return toggle;
    }
}
