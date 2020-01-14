package com.jamesscn.hyrule.tool;

import com.jamesscn.hyrule.init.ModItemGroups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class SwordRazor extends SwordItem {

    public SwordRazor() {
        super(new ModItemTier(3, 100, 8.0F, 3.0F, 0, null), 4, -1.8F, new Item.Properties().group(ModItemGroups.ZeldaItems));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        ITextComponent description = new StringTextComponent("Strong but slightly unstable");
        description.applyTextStyle(TextFormatting.GRAY);
        tooltip.add(description);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}
