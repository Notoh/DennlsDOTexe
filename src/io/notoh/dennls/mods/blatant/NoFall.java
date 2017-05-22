package io.notoh.dennls.mods.blatant;

import io.notoh.dennls.mods.Module;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/20/2017.
 */
public class NoFall extends Module{

    private boolean toggle = false;

    @Override
    public int getKeyCode() {
        return Keyboard.KEY_O;
    }

    @Override
    public String getName() {
        return "NoFall";
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
    public void onUpdate() {
        if(!this.toggle) {
            return;
        }
        if(getMC().thePlayer.fallDistance > 2.0F) {
            getMC().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        }
    }
}
