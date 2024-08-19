package dev.callumherr.modding.worldlyweapons.common.entities.custom;

import dev.callumherr.modding.worldlyweapons.common.util.WWTags;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class HookEntity extends Projectile {
    public static final EntityDataAccessor<Integer> DATA_HOOKED = SynchedEntityData.defineId(HookEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> DATA_HOOKED_ENTITY = SynchedEntityData.defineId(HookEntity.class, EntityDataSerializers.INT);

    public HookEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
        setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();
        Entity owner = this.getOwner();
        if (owner == null || owner.isRemoved()) {
            this.discard();
            return;
        }

        if (getHookState() == HookState.LOOSE) {
            Vec3 movement = this.getDeltaMovement();
            setPos(this.getX() + movement.x,
                    this.getY() + movement.y,
                    this.getZ() + movement.z);
        } else if (getHookState() == HookState.ENTITY_HOOKED) {
            Entity entity = getHookedEntity(level());
            setPos(entity.getX(), entity.getY() + 0.5d, entity.getZ());
        }

        List<LivingEntity> hitEntities = level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox());
        if (!hitEntities.isEmpty() && !hitEntities.getFirst().is(owner)) {
            setHooked(HookState.ENTITY_HOOKED);
            setHookedEntity(hitEntities.getFirst());
        } else if (!level().getBlockState(this.blockPosition()).is(WWTags.BLOCKS.ANTI_GRAPPLE_SURFACE)) {
            setHooked(HookState.BLOCK_HOOKED);
            this.setDeltaMovement(0, 0, 0);
        }

        double distance = distanceTo(owner);
        if (getHookState() == HookState.ENTITY_HOOKED && distance >= 15d && distance <= 20d) {
            LivingEntity entity = (LivingEntity) getHookedEntity(level());
            entity.hurtMarked = true;
            entity.knockback(0.5d,
                    entity.getX()-owner.getX(),
                    entity.getZ()-owner.getZ());
        }
        if (shouldDiscard(distance)) {
            this.discard();
            return;
        }

        if (getHookState() == HookState.BLOCK_HOOKED) {
            Vec3 subtractedMotion = position().subtract(owner.position());
            Vec3 normalisedMotion = subtractedMotion.normalize().scale(0.25d);
            Vec3 ownerNewMotion = owner.getDeltaMovement().scale(0.8d).add(normalisedMotion);
            owner.setDeltaMovement(ownerNewMotion);
        }
    }

    private boolean shouldDiscard(double distance) {
        boolean flag1 = getHookState() == HookState.ENTITY_HOOKED && distance > 20d;
        boolean flag2 = distance >= 30d ;
        //|| (getHookState() == HookState.BLOCK_HOOKED && distance <= 2d)
        return flag1 || flag2;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_HOOKED, HookState.LOOSE.ordinal());
        builder.define(DATA_HOOKED_ENTITY, -1);
    }

    private void setHooked(HookState state) {
        entityData.set(DATA_HOOKED, state.ordinal());
    }

    private HookState getHookState() {
        return HookState.get(entityData.get(DATA_HOOKED));
    }

    private void setHookedEntity(Entity entity) {
        entityData.set(DATA_HOOKED_ENTITY, entity.getId());
    }

    public Entity getHookedEntity(Level level) {
        return level.getEntity(entityData.get(DATA_HOOKED_ENTITY));
    }

    public enum HookState {
        BLOCK_HOOKED,
        ENTITY_HOOKED,
        LOOSE;

        public static HookState get(int i) {
            return values()[i];
        }
    }
}
