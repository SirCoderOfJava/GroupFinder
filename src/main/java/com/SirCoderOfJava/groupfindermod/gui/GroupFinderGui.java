package com.SirCoderOfJava.groupfindermod.gui;


import com.SirCoderOfJava.groupfindermod.gfserver.GroupInfoParser;
import com.SirCoderOfJava.groupfindermod.gui.buttons.CloseButton;
import com.SirCoderOfJava.groupfindermod.gui.buttons.GroupFinderButton;
import com.SirCoderOfJava.groupfindermod.gui.buttons.RefreshGroupsButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.io.IOException;
import java.util.HashMap;

public class GroupFinderGui extends GuiScreen {

    CloseButton closeButton;
    final int CLOSEBUTTONID = 0;
    final int REFRESHBUTTONID = 1;

    public static String groupText = "base";

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int centerX = width / 2;
        int centerY = height / 2;

        drawDefaultBackground();

        GlStateManager.pushMatrix();
        { //Title rendering block
            float titleScaleFactor = 2.0F;
            GlStateManager.enableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.scale(titleScaleFactor, titleScaleFactor, titleScaleFactor);
            drawCenteredString(fontRendererObj, "Group Finder", (int) (centerX / titleScaleFactor), 5, 0xFF0000FF);
            drawVerticalLine((int) (centerX / titleScaleFactor), 0, height, 0x4000FF00);
        }
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        { //groups rendering block
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.translate((float) width / 2, 20, 0);
            drawCenteredString(fontRendererObj, groupText, 0, 0, 0xFFFFFFFF);
        }
        GlStateManager.popMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        for(GuiButton b : buttonList) {
            if(b instanceof GroupFinderButton && b.id == button.id) {
                ((GroupFinderButton) button).runClickAction();
            }
        }
        super.actionPerformed(button);
    }

    @Override
    public void initGui() {
        buttonList.clear();
        buttonList.add(new CloseButton(CLOSEBUTTONID, fontRendererObj));
        buttonList.add(new RefreshGroupsButton(REFRESHBUTTONID, fontRendererObj));
        super.initGui();
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }


}
