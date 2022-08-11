package com.SirCoderOfJava.groupfindermod.commands;

import com.SirCoderOfJava.groupfindermod.gfserver.GFHttpRequestHandler;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import java.io.IOException;

public class GetGroupsSubcommand implements SubcommandAction {

    public boolean shouldExecute(String[] args) {
        return (args.length != 0) && args[0].equalsIgnoreCase("groups");
    }

    public void execute() {
        GFHttpRequestHandler requestHandler = new GFHttpRequestHandler(Minecraft.getMinecraft().thePlayer.getName());
        try {
            JsonObject groups = requestHandler.getGroups();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(groups.toString()));
        } catch (IOException e) {
            e.printStackTrace();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("error getting groups"));
        }

    }
}
