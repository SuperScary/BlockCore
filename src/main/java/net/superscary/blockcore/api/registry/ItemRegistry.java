package net.superscary.blockcore.api.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.superscary.blockcore.api.item.BaseItem;
import net.superscary.blockcore.api.tab.Tab;
import net.superscary.blockcore.api.types.ItemDefinition;
import net.superscary.blockcore.core.BlockCore;
import net.superscary.blockcore.core.BlockCoreBase;
import net.superscary.blockcore.core.key.CreativeTabKey;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class ItemRegistry {

    private static final List<ItemDefinition<?>> ITEMS = new ArrayList<>();

    public static final ItemDefinition<BaseItem> TEST_ITEM = item("test_item", BaseItem::new);

    public static List<ItemDefinition<?>> getItems () {
        return Collections.unmodifiableList(ITEMS);
    }

    static <T extends Item> ItemDefinition<T> item (String name, Function<Item.Properties, T> factory) {
        return item(name, BlockCore.getResource(name), factory, CreativeTabKey.MAIN);
    }

    static <T extends Item> ItemDefinition<T> item (String name, ResourceLocation id, Function<Item.Properties, T> factory) {
        return item(name, id, factory, CreativeTabKey.MAIN);
    }

    static <T extends Item> ItemDefinition<T> item (String name, ResourceLocation id, Function<Item.Properties, T> factory, @Nullable ResourceKey<CreativeModeTab> group) {

        Item.Properties p = new Item.Properties();

        T item = factory.apply(p);

        ItemDefinition<T> definition = new ItemDefinition<>(name, id, item);

        if (Objects.equals(group, CreativeTabKey.MAIN)) {
            BlockCoreBase.mainTab.add(definition);
        } else if (group != null) {
            BlockCoreBase.mainTab.addExternal(group, definition);
        }

        ITEMS.add(definition);

        return definition;
    }

    public static void init () {

    }

}
