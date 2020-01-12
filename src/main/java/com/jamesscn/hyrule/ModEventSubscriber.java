package com.jamesscn.hyrule;

import com.jamesscn.hyrule.init.ModItemGroups;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = HyruleMod.M_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
			setup(new Item(new Item.Properties().group(ModItemGroups.ZeldaItems)), "rupee_green"),
			setup(new Item(new Item.Properties().group(ModItemGroups.ZeldaItems)), "rupee_blue"),
			setup(new Item(new Item.Properties().group(ModItemGroups.ZeldaItems)), "rupee_yellow"),
			setup(new Item(new Item.Properties().group(ModItemGroups.ZeldaItems)), "rupee_red"),
			setup(new Item(new Item.Properties().group(ModItemGroups.ZeldaItems)), "rupee_purple"),
			setup(new Item(new Item.Properties().group(ModItemGroups.ZeldaItems)), "rupee_orange"),
			setup(new Item(new Item.Properties().group(ModItemGroups.ZeldaItems)), "rupee_silver")
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