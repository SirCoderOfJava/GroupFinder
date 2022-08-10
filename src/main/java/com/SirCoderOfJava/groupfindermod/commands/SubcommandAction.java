package com.SirCoderOfJava.groupfindermod.commands;

public interface SubcommandAction {
    public void execute();
    public boolean shouldExecute(String[] args);
}
