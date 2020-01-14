package com.jamesscn.hyrule.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IWorld;

public class DekuStick extends ModItem {

    public boolean lit = false;

    public DekuStick() {
        super("Right click on a fire to light", TextFormatting.GRAY, 1);
        addPropertyOverride(new ResourceLocation("type"), (stack, world, livingEntity) -> stack.getDamage());
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        IWorld world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockPos pos1 = pos.offset(context.getFace());
        PlayerEntity player = context.getPlayer();
        if(world.getBlockState(pos1).getBlock() == Blocks.FIRE && !player.isInWaterRainOrBubbleColumn()) {
            player.swingArm(context.getHand());
            if(!world.isRemote()) {
                setDamage(context.getItem(), 1);
                lit = true;
            }
        } else if (lit) {
            if (FlintAndSteelItem.func_219996_a(world.getBlockState(pos1), world, pos1)) {
                BlockState bs1 = ((FireBlock)Blocks.FIRE).getStateForPlacement(world, pos1);
                world.setBlockState(pos1, bs1, 11);
                ItemStack stack = context.getItem();
                if (player instanceof ServerPlayerEntity) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)player, pos1, stack);
                }
            } else if (FlintAndSteelItem.isUnlitCampfire(world.getBlockState(pos))) {
                world.setBlockState(pos, world.getBlockState(pos).with(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
            }
        }
        return super.onItemUse(context);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if(lit) {
            entity.setFire(6);
            return true;
        }
        return false;
    }
}
