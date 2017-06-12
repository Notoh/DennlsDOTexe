package io.notoh.dennls.gui;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.mods.ChangeMode;
import io.notoh.dennls.mods.ClickGui;
import io.notoh.dennls.mods.Mode;
import io.notoh.dennls.mods.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.Display;

/**
 * Client gui class
 * @author Notoh
 */
public class ClientGui extends GuiIngame {

    protected Minecraft mc;

    public ClientGui() {
        super(Minecraft.getMinecraft());
        this.mc = Minecraft.getMinecraft();
    }

    public void renderGameOverlay(float partialTicks) {
        super.renderGameOverlay(partialTicks);
        if(Dennls.getActive() == Mode.GHOST) return;
        Display.setTitle("DennlsDOTExe (test build 2.0.5)");
        this.mc.entityRenderer.setupOverlayRendering();
        GlStateManager.enableBlend();
        drawString(mc.fontRendererObj,"\247f\247lDennls\2475.\2474\247lExe",2,2,0xffffffff);

        int count = 1;
        for(Module module : Dennls.getClient().getMods()) {
            if(!module.getToggle() || module instanceof ChangeMode || module instanceof ClickGui) {
                continue;
            }

            String color = "\247" + count;
            drawString(mc.fontRendererObj,color + module.getName(),2, 2 + (count * 11), 0xffffffff);
            count++;
        }
        drawString(mc.fontRendererObj,"Mode: " + Dennls.getActive().name(),4, 175,  0x49E20E);
        UIRenderer.renderAndUpdateFrames();
    }

}
