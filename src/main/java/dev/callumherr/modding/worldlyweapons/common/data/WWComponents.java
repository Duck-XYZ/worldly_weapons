package dev.callumherr.modding.worldlyweapons.common.data;

import com.mojang.serialization.Codec;
import dev.callumherr.modding.worldlyweapons.WorldlyWeapons;
import dev.callumherr.modding.worldlyweapons.common.data.components.HookComp;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class WWComponents {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(WorldlyWeapons.MOD_ID);

    public static final Supplier<DataComponentType<HookComp>> HOOK = COMPONENTS.registerComponentType("hook",
            builder -> builder.persistent(HookComp.CODEC).networkSynchronized(HookComp.STREAM_CODEC));
}
