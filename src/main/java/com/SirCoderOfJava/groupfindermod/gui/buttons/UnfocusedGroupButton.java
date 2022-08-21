package com.SirCoderOfJava.groupfindermod.gui.buttons;

import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import com.SirCoderOfJava.groupfindermod.gui.PageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

import java.util.List;

/**
 * This class represents a preview for a group in the gui. It is a button to make it easier to implement behavior when it is clicked. It also has its own rendering which is why it extends {@link GuiButton} and not {@link GroupFinderButton}
 */
public class UnfocusedGroupButton extends GuiButton {

    public static final int HALF_TRANSPARENT_BLACK = 0x80000000;
    private final int OPAQUE_WHITE = 0xFFFFFFFF;
    private final int OPAQUE_BLACK = 0xFF000000;

    /**
     * Stores all the lines to be displayed for the preview
     */
    private List<String> lines;
    private static final int SIDE_BUFFER = 5;

    private FontRenderer fontRenderer;

    private static PageHandler handler = GroupFinderGui.pageHandler;

    /**
     * Implements constructor for superclass with inputs for {@code x} and {@code y} so that an external class can handle positioning for multiple different group previews
     * @param buttonId id that the mod stores for the button
     * @param x x coordinate
     * @param y y coordinate
     * @param lines {@link List} of lines to be displayed
     * @param fontRenderer {@link FontRenderer} instance
     */
    public UnfocusedGroupButton(int buttonId, int x, int y, List<String> lines, FontRenderer fontRenderer) {
        super(buttonId, x, y, fontRenderer.getStringWidth((handler.getLongestLineInGroupLines(lines)) + (2 * SIDE_BUFFER)), (lines.size() * 15) + (2 * SIDE_BUFFER), "");

        this.lines = lines;
        this.fontRenderer = fontRenderer;
    }

    /**
     * Custom rendering
     * @param mc {@link Minecraft} instance
     * @param mouseX x coordinate of mouse
     * @param mouseY y coordinate of mouse
     */
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        GlStateManager.pushMatrix();
        { //OpenGL rendering block

            //Enables transparent rendering
            GlStateManager.enableAlpha();

            //Move renderer to top left corner of the button to simplify coordinates
            GlStateManager.translate(xPosition, yPosition, 0);

            //Draw the background of the preview
            drawRect(0, 0, width, height, HALF_TRANSPARENT_BLACK);

            //Render each line in the lines object with a buffer in between
            int currYPos = SIDE_BUFFER;
            for (String line : lines) {
                drawString(fontRenderer, line, SIDE_BUFFER, currYPos, OPAQUE_WHITE);
                currYPos += 15;
            }

            //If the group is hovered over, outlline it in solid black
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

    /**
     * Will use this method later to detect when the user clicks on a group and expand it to offer more details
     */
    public void expand() {

    }


}
