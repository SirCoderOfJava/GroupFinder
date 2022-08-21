package com.SirCoderOfJava.groupfindermod.commands;

import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import com.SirCoderOfJava.groupfindermod.util.TickDelay;
import net.minecraft.client.Minecraft;

public class BlankSubcommand implements SubcommandAction{
    /**
     * This particular subcommand should open the gui
     */
    @Override
    public void execute() {
        //You have to have a 1 tick delay when displaying the gui, otherwise the game chat closing will also close your gui
        //Thanks to cowlection source code for helping me figure this out
        new TickDelay(new Runnable() {
            public void run() {
                Minecraft.getMinecraft().displayGuiScreen(new GroupFinderGui());
            }
        }, 1);
    }

    /**
     * @param args The arguments array from the command
     * @return Boolean flag for whether the command should execute
     *
     * This command should execute when no arguments have been entered
     */
    @Override
    public boolean shouldExecute(String[] args) {
        return (args.length == 0) || args[0].equalsIgnoreCase(""); //redundancy for different ways zero args could show up
    }


}
