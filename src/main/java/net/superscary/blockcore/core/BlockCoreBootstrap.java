package net.superscary.blockcore.core;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(BlockCore.MODID)
public class BlockCoreBootstrap {

    public BlockCoreBootstrap (IEventBus modEventBus) {
        switch (FMLEnvironment.dist) {
            case CLIENT -> new BlockCoreClient(modEventBus);
            case DEDICATED_SERVER -> new BlockCoreServer(modEventBus);
        }
    }

}
