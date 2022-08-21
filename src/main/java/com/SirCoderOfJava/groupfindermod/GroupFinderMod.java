package com.SirCoderOfJava.groupfindermod;

import com.SirCoderOfJava.groupfindermod.commands.MainCommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Main Mod class
 */
@Mod(modid = GroupFinderMod.MODID, version = GroupFinderMod.VERSION)
public class GroupFinderMod {
    public static final String MODID = "groupfindermod";
    public static final String VERSION = "1.0.0";

    /**
     * Initialization method for the mod
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        //register client side command
        ClientCommandHandler.instance.registerCommand(new MainCommand());
    }
}
