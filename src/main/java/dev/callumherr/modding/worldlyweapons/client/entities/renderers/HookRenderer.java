package dev.callumherr.modding.worldlyweapons.client.entities.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dev.callumherr.modding.worldlyweapons.WorldlyWeapons;
import dev.callumherr.modding.worldlyweapons.client.entities.models.HookModel;
import dev.callumherr.modding.worldlyweapons.client.entities.LayerLocations;
import dev.callumherr.modding.worldlyweapons.common.entities.custom.HookEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class HookRenderer extends EntityRenderer<HookEntity> {
    protected HookModel model;

    public HookRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new HookModel(context.bakeLayer(LayerLocations.HOOK_LAYER));
        this.shadowRadius = 1f;
    }

    @Override
    public ResourceLocation getTextureLocation(HookEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(WorldlyWeapons.MOD_ID, "textures/entity/hook.png");
    }

    public void render(HookEntity entity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTick, entity.yRotO, entity.getYRot()) - 90.0F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTick, entity.xRotO, entity.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(this.getTextureLocation(entity)), false, false);

        this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1);
        pPoseStack.popPose();
        super.render(entity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}
