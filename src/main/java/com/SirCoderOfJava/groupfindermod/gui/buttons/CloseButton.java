package com.SirCoderOfJava.groupfindermod.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class CloseButton extends GroupFinderButton {
    public CloseButton(int buttonId, FontRenderer fontRenderer) {
        super(buttonId, 10, 10, 90, 15, "Close Menu", fontRenderer);
    }

    public void runClickAction() {
        Minecraft.getMinecraft().displayGuiScreen(null);
    }
}
