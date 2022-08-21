package com.SirCoderOfJava.groupfindermod.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import static com.SirCoderOfJava.groupfindermod.util.ChatMessageColorizer.replaceCodes;

public class HelpSubcommand implements SubcommandAction{

    /**
     * @param args The arguments array from the command
     * @return Boolean flag for whether the command should execute;
     * This command should execute when the player enters "help" as the first argument
     */
    public boolean shouldExecute(String[] args) {
        return (args.length != 0) && args[0].equalsIgnoreCase("help");
    }

    /**
     * When this command executes, it should display a help message.
     *
     * This message is a temporary message; it needs to be replaced with a proper help message.
     */
    public void execute() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(replaceCodes("&6&lUse &r&c/groupfinder&6&l to get started")));
    }
}
