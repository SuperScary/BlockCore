package net.superscary.blockcore.core.init;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.superscary.blockcore.api.registry.BlockRegistry;
import net.superscary.blockcore.api.types.BlockDefinition;

public class InitBlocks {

    private InitBlocks () {

    }

    public static void init (Registry<Block> registry) {
        for (BlockDefinition<?> definition : BlockRegistry.getBlocks()) {
            Registry.register(registry, definition.getId(), definition.block());
        }
    }

}
