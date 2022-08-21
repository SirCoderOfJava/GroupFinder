package com.SirCoderOfJava.groupfindermod.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

import static com.SirCoderOfJava.groupfindermod.util.ChatMessageColorizer.replaceCodes;

/**
 * This is a template for Gui Buttons for the mod. It acts as a {@link GuiButton}, but it implements custom rendering and requires subclasses to implement a method {@code runClickAction()} to make it easier to manage action execution
 */
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

    /**
     * This is a method that must be implemented by all Group Finder Buttons and will be called when they are clicked
     */
    public abstract void runClickAction();

    /**
     * Custom rendering for the all of this mod's buttons
     * @param mc {@link Minecraft} instance
     * @param mouseX X coordinate of mouse
     * @param mouseY Y coordinate of mouse
     */
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        //Don't draw the button if it is hidden
        if(!visible) { return; }

        //Detect if the mouse is hovered over the button
        boolean hovered = ((xPosition <= mouseX) && (mouseX <= xPosition + width)) && ((yPosition <= mouseY) && (mouseY <= yPosition + height));

        GlStateManager.pushMatrix();
        { //OpenGL rendering block for the button

            //Turn on transparent rendering for certain elements
            GlStateManager.enableAlpha();

            //Move the renderer to the top left corner of the button to simplify coordinates
            GlStateManager.translate(xPosition, yPosition, 0);

            //Draw the background of the button in half transparent black
            drawRect(0, 0, width, height, HALF_TRANSPARENT_BLACK);

            //Draw the text of the button in red if the mouse is hovered over it
            //Draw the text in white if the mouse is not hovered over it
            if(hovered) {
                fontRendererObj.drawStringWithShadow(replaceCodes("&c" + displayString), TEXT_X_OFFSET, TEXT_Y_OFFSET, OPAQUE_WHITE);
            }else{
                fontRendererObj.drawString(displayString, TEXT_X_OFFSET, TEXT_Y_OFFSET, OPAQUE_WHITE);
            }

            //Draw the border of the button
            drawHorizontalLine(0, width, 0, OPAQUE_BLACK);
            drawHorizontalLine(0, width, height, OPAQUE_BLACK);
            drawVerticalLine(0, 0, height, OPAQUE_BLACK);
            drawVerticalLine(width, 0, height, OPAQUE_BLACK);
        }
        GlStateManager.popMatrix();
    }
}
