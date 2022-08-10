package com.SirCoderOfJava.groupfindermod.gui.buttons;

import com.SirCoderOfJava.groupfindermod.gfserver.GFHttpRequestHandler;
import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ChatComponentText;

import java.io.IOException;

public class RefreshGroupsButton extends GroupFinderButton {

    GFHttpRequestHandler requestHandler;

    public RefreshGroupsButton(int buttonId, FontRenderer fontRenderer) {
        super(buttonId, 110, 10, 90, 15, "Refresh", fontRenderer);
        requestHandler = new GFHttpRequestHandler(Minecraft.getMinecraft().thePlayer.getName());
    }
    public void runClickAction() {
        try {
            JsonObject groups = requestHandler.getGroups();
            GroupFinderGui.groupText = groups.toString();
        } catch (IOException e) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Error getting groups"));
        }
    }
}
