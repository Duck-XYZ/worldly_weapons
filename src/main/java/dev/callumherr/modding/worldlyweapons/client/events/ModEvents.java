package dev.callumherr.modding.worldlyweapons.client.events;

import dev.callumherr.modding.worldlyweapons.WorldlyWeapons;
import dev.callumherr.modding.worldlyweapons.client.entities.LayerLocations;
import dev.callumherr.modding.worldlyweapons.client.entities.models.HookModel;
import dev.callumherr.modding.worldlyweapons.client.entities.renderers.HookRenderer;
import dev.callumherr.modding.worldlyweapons.common.entities.WWEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = WorldlyWeapons.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EntityRenderers.register(WWEntities.REAPERS_HOOK_ENTITY.get(), HookRenderer::new);
        });
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LayerLocations.HOOK_LAYER, HookModel::createBodyLayer);
    }
}
