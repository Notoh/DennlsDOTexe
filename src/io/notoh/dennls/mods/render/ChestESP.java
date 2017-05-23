package io.notoh.dennls.mods.render;

import io.notoh.dennls.mods.Module;
import io.notoh.dennls.util.ModCategory;
import io.notoh.dennls.util.RenderUtils;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/22/2017.
 */
public class ChestESP extends Module {

    private int keycode = Keyboard.KEY_P;
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
    public void onRender() {
        if(!this.toggle) return;
        for(Object object : getMC().theWorld.loadedTileEntityList) {
             if(object instanceof TileEntityChest) {
                 RenderUtils.blockESPBox(((TileEntityChest)object).getPos());
             }
             if(object instanceof TileEntityEnderChest) {
                 RenderUtils.blockESPBox(((TileEntityEnderChest)object).getPos());
             }
        }
    }

    @Override
    public String getName() {
        return "ChestESP";
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
        return ModCategory.RENDER;
    }
}
