package com.SirCoderOfJava.groupfindermod.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class CloseButton extends GroupFinderButton {
    public CloseButton(int buttonId, FontRenderer fontRenderer) {
        super(buttonId, 10, 10, 90, 15, "Close Menu", fontRenderer);
    }

    /*@Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(!visible) { return; }

        boolean hovered = ((xPos <= mouseX) && (mouseX <= xMax)) && ((yPos <= mouseY) && (mouseY <= yMax));
        GlStateManager.pushMatrix();
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.translate(xPos, yPos, 0);
            drawRect(0, 0, customWidth, customHeight, 0xFF0000FF);
            if(hovered) {
                fontRendererObj.drawStringWithShadow(dispText, 5, 4, 0xFF00FFFF);
            }else{
                fontRendererObj.drawString(dispText, 5, 4, 0xFF00FFFF);
            }
            int color = hovered ? 0xFF00FFFF : 0xFF000000;
            drawHorizontalLine(0, customWidth, 0, color);
            drawHorizontalLine(0, customWidth, customHeight, color);
            drawVerticalLine(0, 0, customHeight, color);
            drawVerticalLine(customWidth, 0, customHeight, color);
        }
        GlStateManager.popMatrix();

    }*/

    public void runClickAction() {
        Minecraft.getMinecraft().displayGuiScreen(null);
    }
}
