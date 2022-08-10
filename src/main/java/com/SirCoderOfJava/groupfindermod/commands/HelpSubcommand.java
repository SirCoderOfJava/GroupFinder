package com.SirCoderOfJava.groupfindermod.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import static com.SirCoderOfJava.groupfindermod.util.ChatMessageColorizer.replaceCodes;

public class HelpSubcommand implements SubcommandAction{

    public boolean shouldExecute(String[] args) {
        return (args.length != 0) && args[0].equalsIgnoreCase("help");
    }

    public void execute() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(replaceCodes("&6&lTest Help Message")));
    }
}
