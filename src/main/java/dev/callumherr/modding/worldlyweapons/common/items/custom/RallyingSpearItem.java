package dev.callumherr.modding.worldlyweapons.common.items.custom;

import dev.callumherr.modding.worldlyweapons.common.effects.WWEffects;
import dev.callumherr.modding.worldlyweapons.common.items.WWItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

import java.util.List;

public class RallyingSpearItem extends SwordItem {
    public RallyingSpearItem(Properties properties) {
        super(Tiers.IRON, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        List<Player> players = level.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(5, 0, 5));
        for (Player target : players) {
            target.addEffect(new MobEffectInstance(WWEffects.RALLYING_CRY, 2400));
        }

        player.getCooldowns().addCooldown(WWItems.RALLYING_SPEAR.asItem(), 4800);
        return super.use(level, player, usedHand);
    }
}
