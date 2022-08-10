package com.SirCoderOfJava.groupfindermod.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.Arrays;

public class MainCommand extends CommandBase {

    SubcommandAction[] subcommands = {new HelpSubcommand(), new GetGroupsSubcommand(), new BlankSubcommand()};

    @Override
    public String getCommandName() {
        return "groupfinder";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/groupfinder";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        for(SubcommandAction subcommand : subcommands) {
            if(!subcommand.shouldExecute(args)) { continue; }
            subcommand.execute();
            return;
        }
    }

    @Override
    public ArrayList<String> getCommandAliases() {
        ArrayList<String> aliases = new ArrayList<String>(Arrays.asList("gf", "gfinder"));
        return aliases;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
