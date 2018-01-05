<<<<<<< HEAD
package io.notoh.dennls.mods.ghost;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import io.notoh.dennls.util.TimeHelper;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/23/2017.
 */
public class Criticals extends Module {

    private int keyCode = Keyboard.KEY_SEMICOLON;
    private boolean toggle;

    @Override
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public int getKeyCode() {
        return keyCode;
    }

    @Override
    public String getName() {
        return "Criticals";
    }

    private TimeHelper timer = new TimeHelper();

    @Override
    public void onUpdate() {
        if(!this.toggle) return;
        boolean sprint = getMC().thePlayer.isSprinting();
        Entity entity = null;
        if(getMC().objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            entity = getMC().objectMouseOver.entityHit;
            getMC().thePlayer.setSprinting(true);
            if(getMC().thePlayer.onGround) {
                getMC().thePlayer.onGround = false;
                getMC().thePlayer.motionY = 0.1000000014901161D;
                getMC().thePlayer.onGround = true;
                getMC().thePlayer.setSprinting(true);
            }
            if((getMC().thePlayer.onGround) && (getMC().gameSettings.keyBindAttack.pressed)) {
                C03PacketPlayer packet = new C03PacketPlayer();
                packet.y += 0.2;
                getMC().getNetHandler().addToSendQueue(packet);
                if(timer.isDelayComplete(100L)) {
                    getMC().thePlayer.onGround = true;
                    C03PacketPlayer pack = new C03PacketPlayer();
                    pack.y -= 0.1d;
                    getMC().getNetHandler().addToSendQueue(pack);
                    getMC().thePlayer.motionY -= 0.1d;
                    getMC().thePlayer.setSprinting(sprint);
                }
                timer.reset();
            }
        }
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
=======
package io.notoh.dennls.mods.ghost;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import io.notoh.dennls.util.TimeHelper;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/23/2017.
 */
public class Criticals extends Module {

    private int keyCode = Keyboard.KEY_SEMICOLON;
    private boolean toggle;

    @Override
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public int getKeyCode() {
        return keyCode;
    }

    @Override
    public String getName() {
        return "Criticals";
    }

    private TimeHelper timer = new TimeHelper();

    @Override
    public void onUpdate() {
        if(!this.toggle) return;
        boolean sprint = getMC().thePlayer.isSprinting();
        Entity entity = null;
        if(getMC().objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            entity = getMC().objectMouseOver.entityHit;
            getMC().thePlayer.setSprinting(true);
            if(getMC().thePlayer.onGround) {
                getMC().thePlayer.onGround = false;
                getMC().thePlayer.motionY = 0.1000000014901161D;
                getMC().thePlayer.onGround = true;
                getMC().thePlayer.setSprinting(true);
            }
            if((getMC().thePlayer.onGround) && (getMC().gameSettings.keyBindAttack.pressed)) {
                C03PacketPlayer packet = new C03PacketPlayer();
                packet.y += 0.2;
                getMC().getNetHandler().addToSendQueue(packet);
                if(timer.isDelayComplete(100L)) {
                    getMC().thePlayer.onGround = true;
                    C03PacketPlayer pack = new C03PacketPlayer();
                    pack.y -= 0.1d;
                    getMC().getNetHandler().addToSendQueue(pack);
                    getMC().thePlayer.motionY -= 0.1d;
                    getMC().thePlayer.setSprinting(sprint);
                }
                timer.reset();
            }
        }
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
>>>>>>> origin/master
