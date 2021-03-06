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

public class Rupee extends Item {

    public int value;

    public Rupee(int value) {
        super(new Item.Properties().group(ModItemGroups.ZeldaItems));
        this.value = value;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        ITextComponent description;
        if(value == 1) {
            description = new StringTextComponent("Worth one rupee");
        } else {
            description = new StringTextComponent("Worth " + value + " rupees!");
        }
        if(value < 100) {
            description.applyTextStyle(TextFormatting.GRAY);
        } else {
            description.applyTextStyle(TextFormatting.GOLD);
        }
        tooltip.add(description);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
