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

public class ModItem extends Item {

    String desc;
    TextFormatting colour;

    public ModItem(String description, TextFormatting formatting, int stackSize) {
        super(new Item.Properties().group(ModItemGroups.ZeldaItems).maxStackSize(stackSize));
        desc = description;
        colour = formatting;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        ITextComponent description = new StringTextComponent(desc);
        description.applyTextStyle(colour);
        tooltip.add(description);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
