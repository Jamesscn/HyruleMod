package com.jamesscn.hyrule.init;

import com.jamesscn.hyrule.HyruleMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import java.util.function.Supplier;

public class ModItemGroups {
    public static class ModItemGroup extends ItemGroup {
        private final Supplier<ItemStack> iconSupplier;

        public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier) {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack createIcon() {
            return iconSupplier.get();
        }
    }

    public static final ItemGroup ZeldaItems = new ModItemGroup(HyruleMod.M_ID, () -> new ItemStack(ModItems.rupee_green));
}