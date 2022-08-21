package com.SirCoderOfJava.groupfindermod.commands;

import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import com.SirCoderOfJava.groupfindermod.util.TickDelay;
import net.minecraft.client.Minecraft;

public class GetGroupsSubcommand implements SubcommandAction {

    /**
     * @param args The arguments array from the command
     * @return Boolean flag for whether the command should execute-
     * This command should execute if the first argument is "groups"
     */
    public boolean shouldExecute(String[] args) {
        return (args.length != 0) && args[0].equalsIgnoreCase("groups");
    }

    /**
     * When this command executes, it needs to open the gui to display the groups
     */
    public void execute() {
        new TickDelay(new Runnable() {
            @Override
            public void run() {
                Minecraft.getMinecraft().displayGuiScreen(new GroupFinderGui());
            }
        }, 1);
    }
}
