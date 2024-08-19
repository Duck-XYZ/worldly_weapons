package dev.callumherr.modding.worldlyweapons;

import dev.callumherr.modding.worldlyweapons.common.data.WWComponents;
import dev.callumherr.modding.worldlyweapons.common.entities.WWEntities;
import dev.callumherr.modding.worldlyweapons.client.entities.models.HookModel;
import dev.callumherr.modding.worldlyweapons.client.entities.renderers.HookRenderer;
import dev.callumherr.modding.worldlyweapons.client.entities.LayerLocations;
import dev.callumherr.modding.worldlyweapons.common.items.WWItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(WorldlyWeapons.MOD_ID)
public class WorldlyWeapons {

    public static final String MOD_ID = "worldly_weapons";

    public static ResourceLocation resLoc(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public WorldlyWeapons(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        WWItems.ITEMS.register(modEventBus);
        WWEntities.ENTITIES.register(modEventBus);
        WWComponents.COMPONENTS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = WorldlyWeapons.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                EntityRenderers.register(WWEntities.REAPERS_HOOK_ENTITY.get(), HookRenderer::new);
            });
        }
    }

    @EventBusSubscriber(modid = WorldlyWeapons.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(LayerLocations.HOOK_LAYER, HookModel::createBodyLayer);
        }
    }
}
