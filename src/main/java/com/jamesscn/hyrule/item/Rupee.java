package com.jamesscn.hyrule.item;

import com.jamesscn.hyrule.init.ModItemGroups;
import net.minecraft.item.Item;

public class Rupee extends Item {

    public int value;

    public Rupee(int value) {
        super(new Item.Properties().group(ModItemGroups.ZeldaItems));
        this.value = value;
    }
}
