package dev.callumherr.modding.worldlyweapons.common.util;

import dev.callumherr.modding.worldlyweapons.WorldlyWeapons;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;

public class WWTags {
    public static class BLOCKS {
        public static final TagKey<Block> ANTI_GRAPPLE_SURFACE = createTag("anti_grapple_surface");

        public static TagKey<Block> createTag(String name) {
            return BlockTags.create(WorldlyWeapons.resLoc(name));
        }
    }

    public static class ITEMS {

        public static TagKey<Item> createTag(String name) {
            return ItemTags.create(WorldlyWeapons.resLoc(name));
        }
    }
}
