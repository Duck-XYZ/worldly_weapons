package dev.callumherr.modding.worldlyweapons.common.events;

import dev.callumherr.modding.worldlyweapons.WorldlyWeapons;
import dev.callumherr.modding.worldlyweapons.common.effects.WWEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

@EventBusSubscriber(modid = WorldlyWeapons.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class GameEvents {

    @SubscribeEvent
    public static void livingDamageDealt(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof LivingEntity entity) {
            if (entity.hasEffect(WWEffects.RALLYING_CRY)) event.setNewDamage(event.getNewDamage() * 1.1F);
        }
    }
}
