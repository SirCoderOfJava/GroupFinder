package com.SirCoderOfJava.groupfindermod.gui.buttons;

import com.SirCoderOfJava.groupfindermod.gfserver.GFHttpRequestHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

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
    public void runClickAction() {
        //GroupFinderGui.pageHandler.update();
    }
}
