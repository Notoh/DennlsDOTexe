package org.darkstorm.minecraft.gui.theme.simple;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

/*
 * Copyright (c) 2013, DarkStorm (darkstorm@evilminecraft.net)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

public class UnicodeFontRenderer extends FontRenderer {

    private final UnicodeFont font;

    @SuppressWarnings("unchecked")
    public UnicodeFontRenderer(Font awtFont) {
        super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), false);

        font = new UnicodeFont(awtFont);
        font.addAsciiGlyphs();
        font.getEffects().add(new ColorEffect(Color.WHITE));
        try {
            font.loadGlyphs();
        } catch(SlickException exception) {
            throw new RuntimeException(exception);
        }
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        FONT_HEIGHT = font.getHeight(alphabet) / 2;
    }

    @Override
    public int drawString(String string, int x, int y, int color) {
        if(string == null)
            return 0;
        // glClear(256);
        // glMatrixMode(GL_PROJECTION);
        // glLoadIdentity();
        // IntBuffer buffer = BufferUtils.createIntBuffer(16);
        // glGetInteger(GL_VIEWPORT, buffer);
        // glOrtho(0, buffer.get(2), buffer.get(3), 0, 1000, 3000);
        // glMatrixMode(GL_MODELVIEW);
        // glLoadIdentity();
        // glTranslatef(0, 0, -2000);
        glPushMatrix();
        glScaled(0.5, 0.5, 0.5);

        boolean blend = glIsEnabled(GL_BLEND);
        boolean lighting = glIsEnabled(GL_LIGHTING);
        boolean texture = glIsEnabled(GL_TEXTURE_2D);
        if(!blend)
            glEnable(GL_BLEND);
        if(lighting)
            glDisable(GL_LIGHTING);
        if(texture)
            glDisable(GL_TEXTURE_2D);
        x *= 2;
        y *= 2;
        // glBegin(GL_LINES);
        // glVertex3d(x, y, 0);
        // glVertex3d(x + getStringWidth(string), y + FONT_HEIGHT, 0);
        // glEnd();

        font.drawString(x, y, string, new org.newdawn.slick.Color(color));

        if(texture)
            glEnable(GL_TEXTURE_2D);
        if(lighting)
            glEnable(GL_LIGHTING);
        if(!blend)
            glDisable(GL_BLEND);
        glPopMatrix();
        return x;
    }

    @Override
    public int drawStringWithShadow(String string, float x, float y, int color) {
        return drawString(string, (int) x, (int) y, color);
    }

    @Override
    public int getCharWidth(char c) {
        return getStringWidth(Character.toString(c));
    }

    @Override
    public int getStringWidth(String string) {
        return font.getWidth(string) / 2;
    }

    public int getStringHeight(String string) {
        return font.getHeight(string) / 2;
    }

}
