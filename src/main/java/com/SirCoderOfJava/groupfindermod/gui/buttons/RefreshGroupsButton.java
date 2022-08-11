package com.SirCoderOfJava.groupfindermod.gui.buttons;

import com.SirCoderOfJava.groupfindermod.gfserver.GFHttpRequestHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class RefreshGroupsButton extends GroupFinderButton {

    GFHttpRequestHandler requestHandler;

    public RefreshGroupsButton(int buttonId, FontRenderer fontRenderer) {
        super(buttonId, 110, 10, 90, 15, "Refresh", fontRenderer);
        requestHandler = new GFHttpRequestHandler(Minecraft.getMinecraft().thePlayer.getName());
    }
    public void runClickAction() {
        //GroupFinderGui.pageHandler.update();
    }
}
