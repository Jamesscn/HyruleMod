package com.jamesscn.hyrule.tool;

import com.jamesscn.hyrule.init.ModItemGroups;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class SwordKokiri extends SwordItem {

    public SwordKokiri() {
        super(ItemTier.IRON, 3, -1.8F, new Item.Properties().group(ModItemGroups.ZeldaItems));
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        ITextComponent description = new StringTextComponent("A sword even a child can wield");
        description.applyTextStyle(TextFormatting.GRAY);
        tooltip.add(description);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
