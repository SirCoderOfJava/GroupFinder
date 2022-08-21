package com.SirCoderOfJava.groupfindermod.commands;

public interface SubcommandAction {
    /**
     * The action that the subcommand will perform
     */
    void execute();

    /**
     * @param args The arguments array from the command
     * @return Boolean flag for whether the subcommand should execute
     */
    boolean shouldExecute(String[] args);
}
