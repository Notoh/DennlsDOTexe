package io.notoh.dennls.mods;

import io.notoh.dennls.util.ModCategory;
import net.minecraft.client.Minecraft;

/**
 * Abstract class representing modules.
 * @author Notoh
 */
public abstract class Module {

    public Minecraft getMC() {
        return Minecraft.getMinecraft();
    }

    public void onRender() {
        //do nothing, if this needs to be overridden it will be, this is just here for polymorphism
    }
    public void onEnable() {
        //do nothing, if this needs to be overridden it will be, this is just here for polymorphism
    }
    public void onDisable() {
        //do nothing, if this needs to be overridden it will be, this is just here for polymorphism
    }
    public void onUpdate() {
        //do nothing, if this needs to be overridden it will be, this is just here for polymorphism
    }

    public abstract void setKeyCode(int keyCode);
    public abstract int getKeyCode();
    public abstract String getName();
    public abstract void toggle();
    public abstract boolean getToggle();
    public abstract ModCategory getCategory();

}
