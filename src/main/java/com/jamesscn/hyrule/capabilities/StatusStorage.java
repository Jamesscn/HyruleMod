package com.jamesscn.hyrule.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class StatusStorage implements Capability.IStorage<IStatus> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<IStatus> capability, IStatus instance, Direction side) {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("mana", instance.getMana());
        tag.putInt("manaLimit", instance.getManaLimit());
        tag.putInt("rupees", instance.getRupees());
        tag.putInt("rupeeLimit", instance.getRupeeLimit());
        tag.putInt("heartPieces", instance.getHeartPieces());
        tag.putInt("heartContainers", instance.getHeartContainers());
        return tag;
    }

    @Override
    public void readNBT(Capability<IStatus> capability, IStatus instance, Direction side, INBT nbt) {
        CompoundNBT tag = (CompoundNBT) nbt;
        instance.setManaLimit(tag.getInt("manaLimit"));
        instance.setMana(tag.getInt("mana"));
        instance.setRupeeLimit(tag.getInt("rupeeLimit"));
        instance.setRupees(tag.getInt("rupees"));
        instance.setHeartPieces(tag.getInt("heartPieces"));
        instance.setHeartContainers(tag.getInt("heartContainers"));
    }
}