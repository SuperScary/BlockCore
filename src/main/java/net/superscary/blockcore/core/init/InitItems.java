package net.superscary.blockcore.core.init;

import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.superscary.blockcore.api.registry.BlockRegistry;
import net.superscary.blockcore.api.registry.ItemRegistry;

public class InitItems {

    private InitItems () {

    }

    public static void init (Registry<Item> registry) {
        for (var definition : BlockRegistry.getBlocks()) {
            Registry.register(registry, definition.getId(), definition.asItem());
        }

        for (var definition : ItemRegistry.getItems()) {
            Registry.register(registry, definition.getId(), definition.asItem());
        }
    }

}
