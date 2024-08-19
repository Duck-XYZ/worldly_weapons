package dev.callumherr.modding.worldlyweapons.common.items.custom;

import dev.callumherr.modding.worldlyweapons.common.data.WWComponents;
import dev.callumherr.modding.worldlyweapons.common.data.components.HookComp;
import dev.callumherr.modding.worldlyweapons.common.entities.WWEntities;
import dev.callumherr.modding.worldlyweapons.common.entities.custom.HookEntity;
import dev.callumherr.modding.worldlyweapons.common.items.WWItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ReapersHookItem extends SwordItem {
    public ReapersHookItem(Properties properties) {
        super(Tiers.IRON, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (level.isClientSide) return super.use(level, player, usedHand);

        ItemStack itemStack = player.getItemInHand(usedHand);
        HookComp hookComp = itemStack.get(WWComponents.HOOK.get());

        if (hookComp == null || hookComp.getHook(level) == null) {
            HookEntity hook = shootHook(level, player);
            itemStack.set(WWComponents.HOOK, new HookComp(hook.getId()));
            player.getCooldowns().addCooldown(WWItems.REAPERS_HOOK.asItem(), 30);
            return super.use(level, player, usedHand);
        }

        HookEntity hookEntity = (HookEntity) hookComp.getHook(level);
        if (hookEntity != null && hookEntity.getHookedEntity(level) != null) {
            LivingEntity entity = (LivingEntity) hookEntity.getHookedEntity(level);
            entity.hurtMarked = true;
            //TODO: Needs pull strength based on distance
            entity.knockback(2.5d,
                    entity.getX()-player.getX(),
                    entity.getZ()-player.getZ());
        }
        if (hookEntity != null) hookEntity.discard();
        itemStack.set(WWComponents.HOOK, null);
        player.getCooldowns().addCooldown(WWItems.REAPERS_HOOK.asItem(), 30);

        return super.use(level, player, usedHand);
    }

    private HookEntity shootHook(Level level, Player player) {
        HookEntity hook = WWEntities.REAPERS_HOOK_ENTITY.get().create(level);
        hook.setPos(player.position().add(0, 1, 0));
        hook.setOwner(player);
        hook.shootFromRotation(player,
                player.getXRot(), player.getYRot(), 0,
                1f, 0);
        level.addFreshEntity(hook);
        return hook;
    }

}
