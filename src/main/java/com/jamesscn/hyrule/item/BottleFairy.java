package com.jamesscn.hyrule.item;

import com.jamesscn.hyrule.init.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BottleFairy extends ModItem {

    public BottleFairy() {
        super("When released or upon death, this fairy will come to your aid", TextFormatting.LIGHT_PURPLE, 1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!playerIn.abilities.isCreativeMode) {
            itemstack = new ItemStack(Items.GLASS_BOTTLE, 1);
        }

        playerIn.setHealth(playerIn.getMaxHealth());
        worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, ModSounds.fairy, SoundCategory.NEUTRAL, 0.5F, 1.0F);
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }
}
