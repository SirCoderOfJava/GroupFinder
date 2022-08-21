package com.SirCoderOfJava.groupfindermod.gui.buttons;

import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import net.minecraft.client.gui.FontRenderer;

/**
 * This is a navigation button that decrements the page counter. It uses rendering from {@link GroupFinderButton} and implements the {@code runClickAction()} method for execution.
 */
public class DecrementPageButton extends GroupFinderButton {

    private static final int EXTRA_LEFT_SHIFT = 20;
    private static final int VERTICAL_SHIFT = 38;
    private static final int WIDTH = 14;
    private static final int HEIGHT = 14;
    private static final int FIRST_PAGE = 1;
    private GroupFinderGui context;

    /**
     * Implements superclass constructor using specific values relative to the center of the screen
     * @param buttonId id for the button stored by the mod
     * @param screenWidth width of the entire screen
     * @param screenHeight height of the entire screen
     * @param fontRenderer {@link FontRenderer} instance
     * @param context {@link GroupFinderGui} instance from which the button was created
     */
    public DecrementPageButton(int buttonId, int screenWidth, int screenHeight, FontRenderer fontRenderer, GroupFinderGui context) {
        super(buttonId, (screenWidth / 2) - (fontRenderer.getStringWidth("Page " + GroupFinderGui.pageHandler.getNumberOfPages() + " / " + GroupFinderGui.pageHandler.getNumberOfPages()) / 2) - EXTRA_LEFT_SHIFT, screenHeight - VERTICAL_SHIFT, WIDTH, HEIGHT, "-", fontRenderer);
        this.context = context;
    }

    /**
     * Runs when the button is clicked. Gets the current page, and if it isn't page 1, decrements the current page.
     */
    public void runClickAction() {
        int currPage = context.getCurrentPage();
        if(currPage > FIRST_PAGE) {
            context.setCurrentPage(currPage - 1);
        }
    }
}
