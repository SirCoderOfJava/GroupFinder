package com.SirCoderOfJava.groupfindermod.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

import static com.SirCoderOfJava.groupfindermod.util.ChatMessageColorizer.replaceCodes;

public abstract class GroupFinderButton extends GuiButton {

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
            drawRect(0, 0, width, height, 0x80000000);
            if(hovered) {
                fontRendererObj.drawStringWithShadow(replaceCodes("&c" + displayString), 5, 4, 0xFFFFFFFF);
            }else{
                fontRendererObj.drawString(displayString, 5, 4, 0xFFFFFFFF);
            }
            drawHorizontalLine(0, width, 0, 0xFF000000);
            drawHorizontalLine(0, width, height, 0xFF000000);
            drawVerticalLine(0, 0, height, 0xFF000000);
            drawVerticalLine(width, 0, height, 0xFF000000);
        }
        GlStateManager.popMatrix();
    }
}
