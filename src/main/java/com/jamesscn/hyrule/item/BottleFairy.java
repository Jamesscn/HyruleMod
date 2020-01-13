package com.jamesscn.hyrule.item;

import com.jamesscn.hyrule.init.ModItemGroups;
import com.jamesscn.hyrule.init.ModSounds;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BottleFairy extends Item {

    public BottleFairy() {
        super(new Item.Properties().group(ModItemGroups.ZeldaItems).maxStackSize(1));
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

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        ITextComponent description = new StringTextComponent("When released or upon death, this fairy will come to your aid");
        description.applyTextStyle(TextFormatting.DARK_PURPLE);
        tooltip.add(description);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
