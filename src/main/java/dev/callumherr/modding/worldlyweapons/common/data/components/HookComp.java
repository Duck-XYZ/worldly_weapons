package dev.callumherr.modding.worldlyweapons.common.data.components;

import com.mojang.datafixers.types.templates.Hook;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

public record HookComp(int value1) {
    public static final Codec<HookComp> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("value1").forGetter(HookComp::value1)
            ).apply(instance, HookComp::new));

    public static final StreamCodec<ByteBuf, HookComp> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, HookComp::value1,
            HookComp::new
    );

    public @Nullable Entity getHook(Level level) {
        return level.getEntity(value1);
    }
}
