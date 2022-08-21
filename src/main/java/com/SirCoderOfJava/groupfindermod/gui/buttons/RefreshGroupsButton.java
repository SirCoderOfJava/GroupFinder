package com.SirCoderOfJava.groupfindermod.gui.buttons;

import com.SirCoderOfJava.groupfindermod.gfserver.GFHttpRequestHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

/**
 * Standard button to refresh the gui
 * @see CloseButton
 */
public class RefreshGroupsButton extends GroupFinderButton {

    public static final int X_POSITION = 110;
    public static final int Y_POSITION = 10;
    public static final int WIDTH = 90;
    public static final int HEIGHT = 15;
    GFHttpRequestHandler requestHandler;

    public RefreshGroupsButton(int buttonId, FontRenderer fontRenderer) {
        super(buttonId, X_POSITION, Y_POSITION, WIDTH, HEIGHT, "Refresh", fontRenderer);
        requestHandler = new GFHttpRequestHandler(Minecraft.getMinecraft().thePlayer.getName());
    }

    /**
     * Isn't currently needed as implementation is in {@link com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui}
     */
    public void runClickAction() {

    }
}
