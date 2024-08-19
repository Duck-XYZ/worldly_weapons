package dev.callumherr.modding.worldlyweapons.common.effects;

import dev.callumherr.modding.worldlyweapons.WorldlyWeapons;
import dev.callumherr.modding.worldlyweapons.common.effects.custom.WWMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.awt.*;
import java.util.function.Supplier;

public class WWEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister
            .create(Registries.MOB_EFFECT, WorldlyWeapons.MOD_ID);

    public static final Holder<MobEffect> RALLYING_CRY = EFFECTS.register("rallying_cry",
            () -> new WWMobEffect(MobEffectCategory.BENEFICIAL, 5578058));
}
