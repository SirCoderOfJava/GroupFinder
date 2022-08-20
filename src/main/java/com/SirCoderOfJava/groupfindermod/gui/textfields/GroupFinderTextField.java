package com.SirCoderOfJava.groupfindermod.gui.textfields;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;

public class GroupFinderTextField extends GuiTextField {

    public static final int OPAQUE_WHITE = 0xFFFFFFFF;
    protected final int OPAQUE_BLACK = 0xFF000000;
    protected final int textXOffset = 5;
    protected final int textYOffset = 5;
    protected FontRenderer fontRenderer;

    public GroupFinderTextField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height) {
        super(componentId, fontrendererObj, x, y, par5Width, par6Height);
        fontRenderer = fontrendererObj;
    }

    @Override
    public void drawTextBox() {
        if(this.getVisible()) {
            GlStateManager.pushMatrix();
            {
                GlStateManager.enableBlend();
                GlStateManager.enableAlpha();
                GlStateManager.translate(xPosition, yPosition, 0);
                drawRect(0, 0, width, height, 0xC8000000);
                drawString(fontRenderer, this.getText(), textXOffset, textYOffset, OPAQUE_WHITE);

                if(this.isFocused()) {
                    drawHorizontalLine(0, width, 0, OPAQUE_WHITE);
                    drawHorizontalLine(0, width, height, OPAQUE_WHITE);
                    drawVerticalLine(0, 0, height, OPAQUE_WHITE);
                    drawVerticalLine(width, 0, height, OPAQUE_WHITE);

                    //Cursor
                    String textBeforeCursor = getText().substring(0, getCursorPosition());
                    int cursorOffset = fontRenderer.getStringWidth(textBeforeCursor);
                    drawVerticalLine(cursorOffset + 5, 2, height - 2, OPAQUE_WHITE);
                }else {
                    drawHorizontalLine(0, width, 0, OPAQUE_BLACK);
                    drawHorizontalLine(0, width, height, OPAQUE_BLACK);
                    drawVerticalLine(0, 0, height, OPAQUE_BLACK);
                    drawVerticalLine(width, 0, height, OPAQUE_BLACK);
                }
            }
            GlStateManager.popMatrix();

        }
    }

    public void updateFilter() {

    }
}
