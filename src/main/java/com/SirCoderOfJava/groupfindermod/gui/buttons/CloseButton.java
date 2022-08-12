package com.SirCoderOfJava.groupfindermod.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class CloseButton extends GroupFinderButton {

    public static final int X_POSITION = 10;
    public static final int Y_POSITION = 10;
    public static final int WIDTH = 90;
    public static final int HEIGHT = 15;

    public CloseButton(int buttonId, FontRenderer fontRenderer) {
        super(buttonId, X_POSITION, Y_POSITION, WIDTH, HEIGHT, "Close Menu", fontRenderer);
    }

    public void runClickAction() {
        Minecraft.getMinecraft().displayGuiScreen(null);
    }
}
