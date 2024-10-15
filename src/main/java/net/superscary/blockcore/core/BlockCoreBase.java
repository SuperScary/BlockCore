package net.superscary.blockcore.core;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.superscary.blockcore.api.registry.BlockRegistry;
import net.superscary.blockcore.api.registry.ItemRegistry;
import net.superscary.blockcore.api.tab.Tab;
import net.superscary.blockcore.core.init.InitBlocks;
import net.superscary.blockcore.core.init.InitItems;
import net.superscary.blockcore.core.key.CreativeTabKey;
import net.superscary.blockcore.logger.Log;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;

public abstract class BlockCoreBase implements BlockCore {

    static BlockCoreBase INSTANCE;

    public static Tab mainTab = new Tab(Component.translatable("itemGroup.blockcore"), Items.NETHERITE_HELMET, CreativeTabKey.MAIN);

    public BlockCoreBase (IEventBus modEventBus) {
        if (INSTANCE != null) {
            throw new IllegalStateException();
        }
        INSTANCE = this;

        Log.info("%s Instance Loading.", BlockCore.NAME);

        modEventBus.addListener(mainTab::initExternal);
        modEventBus.addListener((RegisterEvent event) -> {

            if (!event.getRegistryKey().equals(Registries.BLOCK)) {
                return;
            }

            ItemRegistry.init();
            BlockRegistry.init();

            InitBlocks.init(BuiltInRegistries.BLOCK);
            InitItems.init(BuiltInRegistries.ITEM);

            registerCreativeTabs();
            /*if (event.getRegistryKey() == Registries.CREATIVE_MODE_TAB) {
                registerCreativeTabs();
            }*/

        });

        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.addListener(this::onServerAboutToStart);
        NeoForge.EVENT_BUS.addListener(this::serverStopped);
        NeoForge.EVENT_BUS.addListener(this::serverStopping);

    }

    private void registerCreativeTabs () {
        for (Tab tab : Tab.registeredTabs) {
            tab.init(BuiltInRegistries.CREATIVE_MODE_TAB);
        }
    }

    private void commonSetup (FMLCommonSetupEvent event) {
        event.enqueueWork(this::postRegistrationInitialization).whenComplete((res, err) -> {
            if (err != null) {
                Log.warn(err);
            }
        });
    }

    public void postRegistrationInitialization () {

    }

    private void onServerAboutToStart (final ServerAboutToStartEvent evt) {

    }

    private void serverStopping (final ServerStoppingEvent event) {

    }

    private void serverStopped (final ServerStoppedEvent event) {

    }

    @Override
    public Collection<ServerPlayer> getPlayers () {
        var server = getCurrentServer();

        if (server != null) {
            return server.getPlayerList().getPlayers();
        }

        return Collections.emptyList();
    }

    @Nullable
    @Override
    public MinecraftServer getCurrentServer () {
        return ServerLifecycleHooks.getCurrentServer();
    }

}
