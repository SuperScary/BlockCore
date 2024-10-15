package net.superscary.blockcore.api.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.superscary.blockcore.api.block.BaseBlock;
import net.superscary.blockcore.api.block.BaseBlockItem;
import net.superscary.blockcore.api.types.BlockDefinition;
import net.superscary.blockcore.core.BlockCore;
import net.superscary.blockcore.core.BlockCoreBase;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class BlockRegistry {

    public static final List<BlockDefinition<?>> BLOCKS = new ArrayList<>();

    public static final BlockDefinition<Block> TEST_BLOCK = reg("test_block", () -> new Block(Blocks.IRON_BLOCK.properties()));

    public static List<BlockDefinition<?>> getBlocks () {
        return Collections.unmodifiableList(BLOCKS);
    }

    public static <T extends Block> BlockDefinition<T> reg (final String name, final Supplier<T> supplier) {
        return reg(name, BlockCore.getResource(name), supplier, null, true);
    }

    public static <T extends Block> BlockDefinition<T> reg (final String name, ResourceLocation id, final Supplier<T> supplier, boolean addToTab) {
        return reg(name, id, supplier, null, addToTab);
    }

    public static <T extends Block> BlockDefinition<T> reg (final String name, ResourceLocation id, final Supplier<T> supplier, @Nullable BiFunction<Block, Item.Properties, BlockItem> itemFactory, boolean addToTab) {
        T block = supplier.get();
        Item.Properties itemProperties = new Item.Properties();
        BlockItem item;
        if (itemFactory != null) {
            item = itemFactory.apply(block, itemProperties);
            if (item == null) throw new IllegalArgumentException("BlockItem factory for " + id + " returned null.");
        } else if (block instanceof BaseBlock) {
            item = new BaseBlockItem(block, itemProperties);
        } else {
            item = new BlockItem(block, itemProperties);
        }

        BlockDefinition<T> definition = new BlockDefinition<>(name, id, block, item);
        if (addToTab) BlockCoreBase.mainTab.add(definition);
        BLOCKS.add(definition);
        return definition;
    }

    public static void init () {

    }

}
