package com.SirCoderOfJava.groupfindermod.gui.buttons;

import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import com.SirCoderOfJava.groupfindermod.gui.PageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

import java.util.List;

public class UnfocusedGroupButton extends GuiButton {

    public static final int HALF_TRANSPARENT_BLACK = 0x80000000;
    private final int OPAQUE_WHITE = 0xFFFFFFFF;
    private final int OPAQUE_BLACK = 0xFF000000;
    private List<String> lines;
    private static final int SIDE_BUFFER = 5;

    private FontRenderer fontRenderer;

    private static PageHandler handler = GroupFinderGui.pageHandler;

    public UnfocusedGroupButton(int buttonId, int x, int y, List<String> lines, FontRenderer fontRenderer) {
        super(buttonId, x, y, fontRenderer.getStringWidth((handler.getLongestLineInGroupLines(lines)) + (2 * SIDE_BUFFER)), (lines.size() * 15) + (2 * SIDE_BUFFER), "");

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

            drawRect(0, 0, width, height, HALF_TRANSPARENT_BLACK);

            int currYPos = SIDE_BUFFER;
            for (String line : lines) {
                drawString(fontRenderer, line, SIDE_BUFFER, currYPos, OPAQUE_WHITE);
                currYPos += 15;
            }
            if (((xPosition <= mouseX) && (mouseX <= xPosition + width)) &&
                            ((yPosition <= mouseY) && (mouseY <= yPosition + height))) {
                drawVerticalLine(0, 0, height, OPAQUE_BLACK);
                drawVerticalLine(width, 0, height, OPAQUE_BLACK);
                drawHorizontalLine(0, width, 0, OPAQUE_BLACK);
                drawHorizontalLine(0, width, height, OPAQUE_BLACK);
            }
        }
        GlStateManager.popMatrix();
    }

    public void expand() {

    }


}
