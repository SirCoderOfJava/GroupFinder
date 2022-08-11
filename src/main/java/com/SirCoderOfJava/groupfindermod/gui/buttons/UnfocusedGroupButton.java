package com.SirCoderOfJava.groupfindermod.gui.buttons;

import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import com.SirCoderOfJava.groupfindermod.gui.PageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

import java.util.List;

public class UnfocusedGroupButton extends GuiButton {

    private List<String> lines;
    private static final int sideBuffer = 5;

    private FontRenderer fontRenderer;

    private static PageHandler handler = GroupFinderGui.pageHandler;

    public UnfocusedGroupButton(int buttonId, int x, int y, List<String> lines, FontRenderer fontRenderer) {
        super(buttonId, x, y, fontRenderer.getStringWidth((handler.getLongestLineInGroupLines(lines)) + (2 * sideBuffer)), (lines.size() * 15) + (2 * sideBuffer), "");

        this.lines = lines;
        this.fontRenderer = fontRenderer;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        GlStateManager.pushMatrix();
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.translate(xPosition, yPosition, 0);

            drawRect(0, 0, width, height, 0x80000000);

            int currYPos = sideBuffer;
            for (String line : lines) {
                drawString(fontRenderer, line, sideBuffer, currYPos, 0xFFFFFFFF);
                currYPos += 15;
            }
            if (((xPosition <= mouseX) && (mouseX <= xPosition + width)) &&
                            ((yPosition <= mouseY) && (mouseY <= yPosition + height))) {
                drawVerticalLine(0, 0, height, 0xFF000000);
                drawVerticalLine(width, 0, height, 0xFF000000);
                drawHorizontalLine(0, width, 0, 0xFF000000);
                drawHorizontalLine(0, width, height, 0xFF000000);
            }
        }
        GlStateManager.popMatrix();
    }

    public void expand() {

    }


}
