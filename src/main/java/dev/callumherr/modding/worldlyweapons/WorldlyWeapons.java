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
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

}
