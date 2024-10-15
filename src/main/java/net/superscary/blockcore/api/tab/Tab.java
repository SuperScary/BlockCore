package net.superscary.blockcore.api.tab;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.superscary.blockcore.api.block.BaseBlock;
import net.superscary.blockcore.api.block.BaseBlockItem;
import net.superscary.blockcore.api.item.BaseItem;
import net.superscary.blockcore.api.types.ItemDefinition;

import java.util.ArrayList;
import java.util.List;

public class Tab {

    public static ArrayList<Tab> registeredTabs = new ArrayList<>();
    private final Multimap<ResourceKey<CreativeModeTab>, ItemDefinition<?>> externalItemDefs = HashMultimap.create();
    private final List<ItemDefinition<?>> itemDefs = new ArrayList<>();

    private final Component title;
    private final Item displayItem;
    private final ResourceKey<CreativeModeTab> registrationKey;

    public Tab (Component title, ItemDefinition<?> displayItem, ResourceKey<CreativeModeTab> registrationKey) {
        this(title, displayItem.asItem(), registrationKey);
    }

    public Tab (Component title, Item displayItem, ResourceKey<CreativeModeTab> registrationKey) {
        this.title = title;
        this.displayItem = displayItem;
        this.registrationKey = registrationKey;
        registeredTabs.add(this);
    }

    public void init (Registry<CreativeModeTab> registry) {
        var tab = CreativeModeTab.builder()
                .title(getTitle())
                .icon(getDisplayItem()::getDefaultInstance)
                .displayItems(this::buildDisplayItems)
                .build();
        Registry.register(registry, getRegistrationKey(), tab);
    }

    public void initExternal (BuildCreativeModeTabContentsEvent contents) {
        for (var itemDefinition : externalItemDefs.get(contents.getTabKey())) {
            contents.accept(itemDefinition);
        }
    }

    public void add (ItemDefinition<?> itemDef) {
        itemDefs.add(itemDef);
    }

    public void addExternal (ResourceKey<CreativeModeTab> tab, ItemDefinition<?> itemDef) {
        externalItemDefs.put(tab, itemDef);
    }

    private void buildDisplayItems (CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        for (var itemDef : itemDefs) {
            var item = itemDef.asItem();
            if (item instanceof BaseBlockItem baseItem && baseItem.getBlock() instanceof BaseBlock baseBlock) {
                baseBlock.addToCreativeTab(output);
            } else if (item instanceof BaseItem baseItem) {
                baseItem.addToCreativeTab(output);
            } else {
                output.accept(itemDef);
            }
        }
    }

    public Component getTitle () {
        return title;
    }

    public Item getDisplayItem () {
        return displayItem;
    }

    public ResourceKey<CreativeModeTab> getRegistrationKey () {
        return registrationKey;
    }

}
