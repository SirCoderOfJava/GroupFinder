package com.SirCoderOfJava.groupfindermod.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the class for the {@code /groupfinder} command.
 * It encompasses all command behaviors for the mod in one command using subcommands to reduce the possibility of conflict with other mods.
 */
public class MainCommand extends CommandBase {

    /**
     * This list stores all different subcommand objects. These objects encapsulate the logic required to parse the user input and execute their respective actions.
     */
    ArrayList<SubcommandAction> subcommands;

    public MainCommand() {
        subcommands = new ArrayList<SubcommandAction>();
        subcommands.add(new HelpSubcommand());
        subcommands.add(new GetGroupsSubcommand());
        subcommands.add(new BlankSubcommand());
    }

    /**
     * This method tells the mod that the command should execute when the user types {@code /groupfinder} in chat.
     */
    @Override
    public String getCommandName() {
        return "groupfinder";
    }

    /**
     * This method tells the mod the usage of the command
     * @param sender The command sender that executed the command
     */
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/groupfinder";
    }

    /**
     * This method tells the mod what to do to execute the command: loop through every registered subcommand and check if it should execute based on the arguments.
     * If it should execute based on the arguments, execute the command. If not, continue to the next iteration of the loop.
     * @param sender The command sender that executed the command
     * @param args   The arguments that were passed
     */
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        for(SubcommandAction subcommand : subcommands) {
            if(!subcommand.shouldExecute(args)) { continue; }
            subcommand.execute();
            return;
        }
    }

    /**
     * This method tells the mod the aliases for this command.
     * @return An {@code ArrayList<String>} containing the aliases for {@code /gf} and {@code /gfinder}
     */
    @Override
    public ArrayList<String> getCommandAliases() {
        ArrayList<String> aliases = new ArrayList<String>(Arrays.asList("gf", "gfinder"));
        return aliases;
    }

    /**
     * This method ensures that anyone can execute the command. This shouldn't matter since it's a client-side only mod.
     * @param sender The person that sent the command
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
