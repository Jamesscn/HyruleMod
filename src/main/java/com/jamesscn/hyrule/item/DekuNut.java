package com.jamesscn.hyrule.item;

import com.jamesscn.hyrule.init.ModItemGroups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class DekuNut extends Item {

    public DekuNut() {
        super(new Item.Properties().group(ModItemGroups.ZeldaItems));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        ITextComponent description = new StringTextComponent("Stuns enemies when thrown");
        description.applyTextStyle(TextFormatting.GRAY);
        tooltip.add(description);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}
