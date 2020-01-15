package com.jamesscn.hyrule.item;

import com.jamesscn.hyrule.entity.DekuNutEntity;
import com.jamesscn.hyrule.init.ModEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class DekuNut extends ModItem {

    public DekuNut() {
        super("Stuns enemies when thrown", TextFormatting.GRAY, 64);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!playerIn.abilities.isCreativeMode) {
            stack.shrink(1);
        }
        if (!worldIn.isRemote) {
            DekuNutEntity dekuEntity = new DekuNutEntity(ModEntities.deku_nut, playerIn, worldIn);
            dekuEntity.func_213884_b(stack);
            dekuEntity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.addEntity(dekuEntity);
        }
        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }
}
