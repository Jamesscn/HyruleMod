package com.jamesscn.hyrule.armor;

import com.jamesscn.hyrule.init.ModItemGroups;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class TunicKokiri extends ArmorItem {

    public TunicKokiri() {
        super(new ModArmorMaterial("kokiri", 15, new int[]{3, 6, 8, 3}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, () -> {
            return Ingredient.EMPTY;
        }), EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.ZeldaItems));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        ITextComponent description = new StringTextComponent("Full set (only occupies chest slot)");
        description.applyTextStyle(TextFormatting.GRAY);
        tooltip.add(description);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return null;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return null;
    }
}
