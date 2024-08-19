package dev.callumherr.modding.worldlyweapons.common.items.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class RecurveAxe extends AxeItem {
    public RecurveAxe(Properties p_40524_) {
        super(Tiers.IRON, p_40524_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        //TODO: Throws axe when it hits a wall returns to player, if hits entity then slows entity and returns to player
        return super.use(level, player, usedHand);
    }
}
