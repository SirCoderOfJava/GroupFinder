package com.SirCoderOfJava.groupfindermod.gui.textfields;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;

/**
 * Template class for text fields with the mod's custom rendering
 */
public class GroupFinderTextField extends GuiTextField {

    protected static final int OPAQUE_WHITE = 0xFFFFFFFF;
    protected static final int THREE_QUARTERS_TRANSPARENT_BLACK = 0xC8000000;
    protected final int OPAQUE_BLACK = 0xFF000000;
    protected final int textXOffset = 5;
    protected final int textYOffset = 5;
    protected FontRenderer fontRenderer;

    /**
     * Implements superclass constructor and saves the {@link FontRenderer} instance for custom rendering
     * @param componentId id stored by the mod
     * @param fontrendererObj {@link FontRenderer} instance
     * @param x X coordinate
     * @param y Y coordinate
     * @param par5Width width of the field
     * @param par6Height height of the field
     */
    public GroupFinderTextField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height) {
        super(componentId, fontrendererObj, x, y, par5Width, par6Height);
        fontRenderer = fontrendererObj;
    }

    /**
     * Custom rendering for the text field
     */
    @Override
    public void drawTextBox() {
        if(this.getVisible()) {
            GlStateManager.pushMatrix();
            { //OpenGL rendering block

                //Enable transparent rendering
                GlStateManager.enableAlpha();

                //Move to the upper left corner of the text field to make rendering easier
                GlStateManager.translate(xPosition, yPosition, 0);

                //Draw the background of the text field
                drawRect(0, 0, width, height, THREE_QUARTERS_TRANSPARENT_BLACK);

                //Draw the text that the user has entered
                drawString(fontRenderer, this.getText(), textXOffset, textYOffset, OPAQUE_WHITE);

                if(this.isFocused()) {
                    //Draw a white outline around the box
                    drawHorizontalLine(0, width, 0, OPAQUE_WHITE);
                    drawHorizontalLine(0, width, height, OPAQUE_WHITE);
                    drawVerticalLine(0, 0, height, OPAQUE_WHITE);
                    drawVerticalLine(width, 0, height, OPAQUE_WHITE);

                    //Render the cursor
                    String textBeforeCursor = getText().substring(0, getCursorPosition());
                    int cursorOffset = fontRenderer.getStringWidth(textBeforeCursor);
                    drawVerticalLine(cursorOffset + 5, 2, height - 2, OPAQUE_WHITE);
                }else {
                    //Draw a black outline around the box
                    drawHorizontalLine(0, width, 0, OPAQUE_BLACK);
                    drawHorizontalLine(0, width, height, OPAQUE_BLACK);
                    drawVerticalLine(0, 0, height, OPAQUE_BLACK);
                    drawVerticalLine(width, 0, height, OPAQUE_BLACK);
                }
            }
            GlStateManager.popMatrix();

        }
    }

    /**
     * Template method for each text field to update the text filters
     */
    public void updateFilter() {

    }
}
