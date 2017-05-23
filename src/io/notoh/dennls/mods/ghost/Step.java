package io.notoh.dennls.mods.ghost;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/22/2017.
 */
public class Step extends Module {

    private int keycode = Keyboard.KEY_N;
    private boolean toggle;

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
        return "Step";
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
    public ModCategory getCategory() {
        return ModCategory.MOVEMENT;
    }

    @Override
    public boolean getToggle() {
        return toggle;
    }

    @Override
    public void onUpdate() {
        if(!this.toggle) {
            Minecraft.getMinecraft().thePlayer.stepHeight = 0.5f;
            return;
        }
        if(getMC().thePlayer.isCollidedHorizontally && getMC().thePlayer.onGround) {
            getMC().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(getMC().thePlayer.posX, getMC().thePlayer.posY+0.42, getMC().thePlayer.posZ,getMC().thePlayer.onGround));

            getMC().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(getMC().thePlayer.posX, getMC().thePlayer.posY + 0.75, getMC().thePlayer.posZ, getMC().thePlayer.onGround));

            getMC().thePlayer.stepHeight = 1.0f;
        }
    }
}
