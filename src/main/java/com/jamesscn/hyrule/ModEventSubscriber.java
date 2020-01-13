package com.jamesscn.hyrule;

import com.jamesscn.hyrule.init.ModItemGroups;
import com.jamesscn.hyrule.item.*;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = HyruleMod.M_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
			setup(new Rupee(1), "rupee_green"),
			setup(new Rupee(5), "rupee_blue"),
			setup(new Rupee(10), "rupee_yellow"),
			setup(new Rupee(20), "rupee_red"),
			setup(new Rupee(50), "rupee_purple"),
			setup(new Rupee(100), "rupee_orange"),
			setup(new Rupee(200), "rupee_silver"),
			setup(new PotionRed(), "potion_red"),
			setup(new PotionBlue(), "potion_blue"),
			setup(new PotionGreen(), "potion_green"),
			setup(new BottleFairy(), "bottle_fairy"),
			setup(new BottleMilk(), "bottle_milk"),
			setup(new BottleMilkHalf(), "bottle_milk_half"),
			setup(new DekuStick(), "deku_stick"),
			setup(new DekuNut(), "deku_nut")
			//setup(new Item(new Item.Properties().group(ModItemGroups.ZeldaItems)), "deku_nut")
		);
	}

	@SubscribeEvent
	public static void onRegisterSoundEvents(RegistryEvent.Register<SoundEvent> event) {
		event.getRegistry().registerAll(
				setup(new SoundEvent(new ResourceLocation(HyruleMod.M_ID, "fairy")), "fairy")
		);
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(HyruleMod.M_ID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}