package net.superscary.blockcore.core.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.superscary.blockcore.core.BlockCore;

public class CreativeTabKey {

    public static final ResourceKey<CreativeModeTab> MAIN = create("main");

    private CreativeTabKey () {

    }

    private static ResourceKey<CreativeModeTab> create (String path) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, BlockCore.getResource(path));
    }

}
