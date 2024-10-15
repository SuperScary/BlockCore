package net.superscary.blockcore.core;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.superscary.blockcore.logger.Log;

public class BlockCoreClient extends BlockCoreBase {

    public BlockCoreClient (IEventBus modEventBus) {
        super(modEventBus);
        Log.info("%s Client Instance Loading.", BlockCore.NAME);
    }

    @Override
    public Level getClientLevel () {
        return Minecraft.getInstance().level;
    }
}
