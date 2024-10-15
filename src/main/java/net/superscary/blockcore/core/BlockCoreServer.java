package net.superscary.blockcore.core;

import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.superscary.blockcore.logger.Log;

public class BlockCoreServer extends BlockCoreBase {

    public BlockCoreServer (IEventBus modEventBus) {
        super(modEventBus);
        Log.info("%s Server Instance Loading.", BlockCore.NAME);
    }

    @Override
    public Level getClientLevel () {
        return null;
    }
}
