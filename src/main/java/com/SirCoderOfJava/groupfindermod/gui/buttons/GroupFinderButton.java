package com.SirCoderOfJava.groupfindermod.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

import static com.SirCoderOfJava.groupfindermod.util.ChatMessageColorizer.replaceCodes;

public abstract class GroupFinderButton extends GuiButton {

    private final int HALF_TRANSPARENT_BLACK = 0x80000000;
    private final int TEXT_X_OFFSET = 5;
    private final int TEXT_Y_OFFSET = 4;
    private final int OPAQUE_WHITE = 0xFFFFFFFF;
    private final int OPAQUE_BLACK = 0xFF000000;
    FontRenderer fontRendererObj;

    public GroupFinderButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, FontRenderer fontRenderer) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        fontRendererObj = fontRenderer;
    }
    public abstract void runClickAction();

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(!visible) { return; }

        boolean hovered = ((xPosition <= mouseX) && (mouseX <= xPosition + width)) && ((yPosition <= mouseY) && (mouseY <= yPosition + height));
        GlStateManager.pushMatrix();
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.translate(xPosition, yPosition, 0);
            drawRect(0, 0, width, height, HALF_TRANSPARENT_BLACK);
            if(hovered) {
                fontRendererObj.drawStringWithShadow(replaceCodes("&c" + displayString), TEXT_X_OFFSET, TEXT_Y_OFFSET, OPAQUE_WHITE);
            }else{
                fontRendererObj.drawString(displayString, TEXT_X_OFFSET, TEXT_Y_OFFSET, OPAQUE_WHITE);
            }
            drawHorizontalLine(0, width, 0, OPAQUE_BLACK);
            drawHorizontalLine(0, width, height, OPAQUE_BLACK);
            drawVerticalLine(0, 0, height, OPAQUE_BLACK);
            drawVerticalLine(width, 0, height, OPAQUE_BLACK);
        }
        GlStateManager.popMatrix();
    }
}
