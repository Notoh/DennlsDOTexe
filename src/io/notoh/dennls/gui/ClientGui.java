package io.notoh.dennls.gui;

import io.notoh.dennls.ClientEntry;
import io.notoh.dennls.mods.ChangeMode;
import io.notoh.dennls.mods.Mode;
import io.notoh.dennls.mods.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.Display;

/**
 * Client gui class
 * @author Notoh
 */
public class ClientGui extends GuiScreen {

    protected Minecraft mc;

    public ClientGui() {
        this.mc = Minecraft.getMC();
    }

    public void renderScreen() {
        if(ClientEntry.getActive() == Mode.GHOST) return;
        Display.setTitle("DennlsDOTExe (test build 1.0.1)");
        String clientName = "\247f\247lDennls\2475.\2474\247lExe";
        //NO SHADOW = mc.fontRenderObj.drawString
        drawString(mc.fontRendererObj,clientName,4,4,0xffffffff);

        int count = 0;
        for(Module module : ClientEntry.getClient().getMods()) {

            if(!module.getToggle() || module instanceof ChangeMode) {
                continue;
            }

            String color = "\247" + count;
            drawString(mc.fontRendererObj,color + module.getName(),4,20+(10*count),0xffffffff);
            count++;
        }
        drawString(mc.fontRendererObj, "Mode: " + ClientEntry.getActive().name(),4, 150,0xffffffff);
    }

}
