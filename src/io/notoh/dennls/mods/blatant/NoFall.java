package io.notoh.dennls.mods.blatant;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/20/2017.
 */
public class NoFall extends Module{

    private boolean toggle = false;
    private int keycode = Keyboard.KEY_O;

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
        return ModCategory.PLAYER;
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
