package net.superscary.blockcore.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import java.util.Collection;

public interface BlockCore {

    String MODID = "blockcore";
    String NAME = "BlockCore";

    static BlockCore instance () {
        return BlockCoreBase.INSTANCE;
    }

    static ResourceLocation getResource (String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, name);
    }

    static ResourceLocation getMinecraftResource (String name) {
        return ResourceLocation.withDefaultNamespace(name);
    }

    Collection<ServerPlayer> getPlayers ();

    Level getClientLevel ();

    MinecraftServer getCurrentServer ();

}
