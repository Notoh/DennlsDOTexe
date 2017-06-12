package io.notoh.dennls.util;

import io.notoh.dennls.mods.render.ESP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL11;

/**
 * Created by alexa on 5/21/2017.
 */
public final class RenderUtils {

    public static void entityESPBox(Entity entity, int mode) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        if(mode == 0)// Enemy
            GL11.glColor4d(
                    1 - Minecraft.getMinecraft().thePlayer
                            .getDistanceToEntity(entity) / 40,
                    Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) / 40,
                    0, 0.5F);
        else if(mode == 1)// Friend
            GL11.glColor4d(0, 0, 1, 0.5F);
        else if(mode == 2)// Other
            GL11.glColor4d(1, 1, 0, 0.5F);
        else if(mode == 3)// Target
            GL11.glColor4d(1, 0, 0, 0.5F);
        else if(mode == 4)// Team
            GL11.glColor4d(0, 1, 0, 0.5F);
        Minecraft.getMinecraft().getRenderManager();
        RenderGlobal.func_181561_a(
                new AxisAlignedBB(
                        entity.boundingBox.minX
                                - 0.05
                                - entity.posX
                                + (entity.posX - Minecraft.getMinecraft()
                                .getRenderManager().renderPosX),
                        entity.boundingBox.minY
                                - entity.posY
                                + (entity.posY - Minecraft.getMinecraft()
                                .getRenderManager().renderPosY),
                        entity.boundingBox.minZ
                                - 0.05
                                - entity.posZ
                                + (entity.posZ - Minecraft.getMinecraft()
                                .getRenderManager().renderPosZ),
                        entity.boundingBox.maxX
                                + 0.05
                                - entity.posX
                                + (entity.posX - Minecraft.getMinecraft()
                                .getRenderManager().renderPosX),
                        entity.boundingBox.maxY
                                + 0.1
                                - entity.posY
                                + (entity.posY - Minecraft.getMinecraft()
                                .getRenderManager().renderPosY),
                        entity.boundingBox.maxZ
                                + 0.05
                                - entity.posZ
                                + (entity.posZ - Minecraft.getMinecraft()
                                .getRenderManager().renderPosZ)));
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
    }


    public static void drawBlockESP(double x, double y, double z, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWidth) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        RenderGlobal.func_181561_a(new AxisAlignedBB(x, y, z, x + 1D, y + 1D, z + 1D));
        GL11.glLineWidth(lineWidth);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        RenderGlobal.func_181561_a(new AxisAlignedBB(x, y, z, x + 1D, y + 1D, z + 1D));
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawSolidBlockESP(double x, double y, double z, float red, float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        RenderGlobal.func_181561_a(new AxisAlignedBB(x, y, z, x + 1D, y + 1D, z + 1D));
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawOutlinedEntityESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        RenderGlobal.func_181561_a(new AxisAlignedBB(x - width, y, z - width, x + width , y + height, z + width));
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }


    public static void drawEntityESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        RenderGlobal.func_181561_a(new AxisAlignedBB(x - width, y, z - width, x + width , y + height, z + width));
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        RenderGlobal.func_181561_a(new AxisAlignedBB(x - width, y, z - width, x + width , y + height, z + width));
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawTracerLine(double x, double y, double z, float red, float green, float blue, float alpha, float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(2);
        GL11.glVertex3d(0.0D, 0.0D + Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0.0D);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    public static void renderPlayer(EntityLivingBase entity, CanRender render) {
        float red = 0.5f;
        float green = 0.5f;
        float blue = 1.0f;

        double xPos = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * Minecraft.getMinecraft().timer
                .renderPartialTicks) - Minecraft.getMinecraft().getRenderManager().renderPosX;
        double yPos = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * Minecraft.getMinecraft().timer
                .renderPartialTicks) - Minecraft.getMinecraft().getRenderManager().renderPosY;
        double zPos = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * Minecraft.getMinecraft().timer
                .renderPartialTicks) - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        if(render instanceof ESP) {
            render.render(red,green,blue,xPos,yPos, zPos, entity.width,entity.height);
            return;
        }
        render.render(red,green,blue,xPos,yPos,zPos);
    }

    public static void renderMob(EntityLivingBase entity, CanRender render) {
        float red = 1.0f;
        float green = 0.5f;
        float blue = 0.5f;

        double xPos = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * Minecraft.getMinecraft().timer
                .renderPartialTicks) - Minecraft.getMinecraft().getRenderManager().renderPosX;
        double yPos = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * Minecraft.getMinecraft().timer
                .renderPartialTicks) - Minecraft.getMinecraft().getRenderManager().renderPosY;
        double zPos = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * Minecraft.getMinecraft().timer
                .renderPartialTicks) - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        if(render instanceof ESP) {
            render.render(red,green,blue,xPos,yPos, zPos, entity.width,entity.height);
            return;
        }
        render.render(red,green,blue,xPos,yPos,zPos);
    }


    public static void renderAnimal(EntityLivingBase entity,CanRender render) {
        float red = 0.5f;
        float green = 1.0f;
        float blue = 0.5f;

        double xPos = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * Minecraft.getMinecraft().timer
                .renderPartialTicks) - Minecraft.getMinecraft().getRenderManager().renderPosX;
        double yPos = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * Minecraft.getMinecraft().timer
                .renderPartialTicks) - Minecraft.getMinecraft().getRenderManager().renderPosY;
        double zPos = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * Minecraft.getMinecraft().timer
                .renderPartialTicks) - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        if(render instanceof ESP) {
            render.render(red,green,blue,xPos,yPos, zPos, entity.width,entity.height);
            return;
        }
        render.render(red,green,blue,xPos,yPos,zPos);
    }

    public static void renderPassive(EntityLivingBase entity, CanRender render) {
        float red = 0.5f;
        float green = 0.5f;
        float blue = 0.5f;

        double xPos = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * Minecraft.getMinecraft().timer
                .renderPartialTicks) - Minecraft.getMinecraft().getRenderManager().renderPosX;
        double yPos = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * Minecraft.getMinecraft().timer
                .renderPartialTicks) - Minecraft.getMinecraft().getRenderManager().renderPosY;
        double zPos = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * Minecraft.getMinecraft().timer
                .renderPartialTicks) - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        if(render instanceof ESP) {
            render.render(red,green,blue,xPos,yPos, zPos, entity.width,entity.height);
            return;
        }

        render.render(red,green,blue,xPos,yPos,zPos);
    }

    public static void blockESPBox(BlockPos blockPos) {
        double x =
                blockPos.getX()
                        - Minecraft.getMinecraft().getRenderManager().renderPosX;
        double y =
                blockPos.getY()
                        - Minecraft.getMinecraft().getRenderManager().renderPosY;
        double z =
                blockPos.getZ()
                        - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(2.0F);
        GL11.glColor4d(0, 1, 0, 0.15F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glColor4d(0, 0, 1, 0.5F);
        RenderGlobal.func_181561_a(new AxisAlignedBB(x, y, z,
                x + 1.0, y + 1.0,z + 1.0));
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
    }
}
