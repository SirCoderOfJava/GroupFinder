package com.SirCoderOfJava.groupfindermod.commands;

import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import com.SirCoderOfJava.groupfindermod.util.TickDelay;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import scala.collection.parallel.ParIterableLike;

public class BlankSubcommand implements SubcommandAction{
    public void execute() {

        //You have to have a 1 tick delay when displaying the gui, otherwise the game chat closing will also close your gui
        //Thanks to cowlection source code for helping me figure this out
        new TickDelay(new Runnable() {
            public void run() {
                Minecraft.getMinecraft().displayGuiScreen(new GroupFinderGui());
            }
        }, 1);
    }

    public boolean shouldExecute(String[] args) {
        return (args.length == 0) || args[0].equalsIgnoreCase("");
    }


}
