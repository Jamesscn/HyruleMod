package com.jamesscn.hyrule.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DekuNutModel<T extends Entity> extends EntityModel<T> {

	private final RendererModel bb_main;

	public DekuNutModel() {
		textureWidth = 16;
		textureHeight = 16;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 7, -2.0F, -1.5F, -3.0F, 4, 3, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.0F, -2.5F, -3.0F, 4, 1, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.0F, 1.5F, -3.0F, 4, 1, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 2.0F, -1.5F, -3.0F, 1, 3, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -3.0F, -1.5F, -3.0F, 1, 3, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 6, 0, -2.0F, -1.5F, 3.0F, 4, 3, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 6, 0, -2.0F, 0.5F, -4.0F, 4, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 6, 0, -2.0F, -1.5F, -4.0F, 4, 1, 1, 0.0F, false));
	}

	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bb_main.render(scale);
	}

}