package com.jamesscn.hyrule.item;

import com.jamesscn.hyrule.init.ModItemGroups;
import com.jamesscn.hyrule.init.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BottleMilk extends Item {

    public BottleMilk() {
        super(new Item.Properties().group(ModItemGroups.ZeldaItems));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (!worldIn.isRemote) {
            entityLiving.curePotionEffects(stack);
            entityLiving.heal(8);
        }

        if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
            stack.shrink(1);
        }

        if (!worldIn.isRemote) {
            entityLiving.clearActivePotions();
        }

        return stack.isEmpty() ? new ItemStack(ModItems.bottle_milk_half) : stack;
    }

    public int getUseDuration(ItemStack stack) {
        return 32;
    }
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }
}