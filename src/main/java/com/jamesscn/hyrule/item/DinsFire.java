package com.jamesscn.hyrule.item;

import com.jamesscn.hyrule.capabilities.StatusProvider;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class DinsFire extends ModItem {

    public DinsFire() {
        super("Warning: Sets fire to everything in the nearby vicinity", TextFormatting.RED, 1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        playerIn.getCapability(StatusProvider.statusCapability).ifPresent(status -> {
           if(status.hasEnoughMana(100)) {
               status.useMana(100);
               AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().grow(5.0D, 2.0D, 5.0D);
               List<LivingEntity> list = worldIn.getEntitiesWithinAABB(LivingEntity.class, axisalignedbb);
               if (!list.isEmpty()) {
                   for(LivingEntity livingEntity : list) {
                       if(livingEntity != playerIn) {
                           livingEntity.setFire(10);
                       }
                   }
               }
               playerIn.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 180, 0));
               for(int x = -5; x <= 5; x++) {
                   for(int y = -2; y <= 2; y++) {
                       for(int z = -5; z <= 5; z++) {
                           if(x != 0 || z != 0) {
                               BlockPos pos = playerIn.getPosition().add(x, y, z);
                               BlockState state = worldIn.getBlockState(pos);
                               if (FlintAndSteelItem.func_219996_a(state, worldIn, pos)) {
                                   BlockState fireState = ((FireBlock) Blocks.FIRE).getStateForPlacement(worldIn, pos);
                                   worldIn.setBlockState(pos, fireState, 11);
                                   if (playerIn instanceof ServerPlayerEntity) {
                                       CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerIn, pos, stack);
                                   }
                               }
                           }
                       }
                   }
               }
           }
        });
        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }
}
