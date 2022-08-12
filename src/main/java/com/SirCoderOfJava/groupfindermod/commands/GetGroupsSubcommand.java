package com.SirCoderOfJava.groupfindermod.commands;

import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import com.SirCoderOfJava.groupfindermod.util.TickDelay;
import net.minecraft.client.Minecraft;

public class GetGroupsSubcommand implements SubcommandAction {

    public boolean shouldExecute(String[] args) {
        return (args.length != 0) && args[0].equalsIgnoreCase("groups");
    }

    public void execute() {
        new TickDelay(new Runnable() {
            @Override
            public void run() {
                Minecraft.getMinecraft().displayGuiScreen(new GroupFinderGui());
            }
        }, 1);
    }
}
