package com.SirCoderOfJava.groupfindermod;

import com.SirCoderOfJava.groupfindermod.commands.MainCommand;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = GroupFinderMod.MODID, version = GroupFinderMod.VERSION)
public class GroupFinderMod {
    public static final String MODID = "groupfindermod";
    public static final String VERSION = "1.0.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new MainCommand());
    }
}
