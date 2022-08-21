package com.SirCoderOfJava.groupfindermod.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

/**
 * This class represents a button that closes the gui. It uses rendering from {@link GroupFinderButton}
 */
public class CloseButton extends GroupFinderButton {

    private static final int X_POSITION = 10;
    private static final int Y_POSITION = 10;
    private static final int WIDTH = 90;
    private static final int HEIGHT = 15;

    /**
     * Implements the constructor for {@link GroupFinderButton} using specific coordinates for this particular button
     * @param buttonId the id for the button that the mod maintains
     * @param fontRenderer {@link FontRenderer} instance
     */
    public CloseButton(int buttonId, FontRenderer fontRenderer) {
        super(buttonId, X_POSITION, Y_POSITION, WIDTH, HEIGHT, "Close Menu", fontRenderer);
    }

    /**
     * When this button is clicked, the gui should close. This method accomplishes this calling {@code displayGuiScreen} with a {@code null} argument
     */
    public void runClickAction() {
        Minecraft.getMinecraft().displayGuiScreen(null);
    }
}
