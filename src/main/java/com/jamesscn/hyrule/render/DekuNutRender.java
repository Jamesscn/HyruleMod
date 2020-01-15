package com.jamesscn.hyrule.render;

import com.jamesscn.hyrule.HyruleMod;
import com.jamesscn.hyrule.entity.DekuNutEntity;
import com.jamesscn.hyrule.model.DekuNutModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public class DekuNutRender extends EntityRenderer<DekuNutEntity> {

    public ResourceLocation texture = new ResourceLocation(HyruleMod.M_ID, "textures/entity/deku_nut/deku_nut.png");
    public final EntityModel<DekuNutEntity> dekuNutModel = new DekuNutModel<DekuNutEntity>();

    public DekuNutRender(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(DekuNutEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef((float)x, (float)y - 0.6F, (float)z);
        //makes the entity spawn facing away from the camera
        GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch), 0.0F, 0.0F, 1.0F);
        this.bindTexture(texture);
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
        }
        dekuNutModel.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.025F);
        if (this.renderOutlines) {
            GlStateManager.tearDownSolidRenderingTextureCombine();
            GlStateManager.disableColorMaterial();
        }
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(DekuNutEntity entity) {
        return texture;
    }
}
