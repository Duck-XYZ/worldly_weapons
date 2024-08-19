package dev.callumherr.modding.worldlyweapons.client.events;

import dev.callumherr.modding.worldlyweapons.WorldlyWeapons;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = WorldlyWeapons.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class GameEvents {
}
