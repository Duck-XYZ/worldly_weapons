package dev.callumherr.modding.worldlyweapons.client.entities;

import dev.callumherr.modding.worldlyweapons.WorldlyWeapons;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class LayerLocations {
    public static final ModelLayerLocation HOOK_LAYER =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(WorldlyWeapons.MOD_ID, "hook_layer"), "hook_layer");
}
