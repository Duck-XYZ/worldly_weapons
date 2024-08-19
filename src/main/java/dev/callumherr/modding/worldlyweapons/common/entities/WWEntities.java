package dev.callumherr.modding.worldlyweapons.common.entities;

import dev.callumherr.modding.worldlyweapons.WorldlyWeapons;
import dev.callumherr.modding.worldlyweapons.common.entities.custom.HookEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WWEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, WorldlyWeapons.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<HookEntity>> REAPERS_HOOK_ENTITY =
            ENTITIES.register("reapers_hook", () -> EntityType.Builder.of(HookEntity::new, MobCategory.MISC)
                    .sized(1f, 1f).noSummon().build("reapers_hook"));
}
