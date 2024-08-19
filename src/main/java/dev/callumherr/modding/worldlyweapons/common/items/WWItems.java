package dev.callumherr.modding.worldlyweapons.common.items;

import dev.callumherr.modding.worldlyweapons.WorldlyWeapons;
import dev.callumherr.modding.worldlyweapons.common.data.WWComponents;
import dev.callumherr.modding.worldlyweapons.common.items.custom.RallyingSpearItem;
import dev.callumherr.modding.worldlyweapons.common.items.custom.ReapersHookItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WWItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WorldlyWeapons.MOD_ID);

    public static final DeferredItem<ReapersHookItem> REAPERS_HOOK = ITEMS.register("reapers_hook",
            () -> new ReapersHookItem(new Item.Properties())); //TODO: Make it bypass blocking damage with shields

    public static final DeferredItem<RallyingSpearItem> RALLYING_SPEAR = ITEMS.register("rallying_spear",
            () -> new RallyingSpearItem(new Item.Properties()));
}
