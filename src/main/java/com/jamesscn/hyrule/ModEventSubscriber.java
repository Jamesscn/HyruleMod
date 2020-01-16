package com.jamesscn.hyrule;

import com.jamesscn.hyrule.entity.DekuNutEntity;
import com.jamesscn.hyrule.item.*;
import com.jamesscn.hyrule.tool.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
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
			setup(new DekuNut(), "deku_nut"),
			setup(new DinsFire(), "dins_fire"),
			setup(new FaroresWind(), "farores_wind"),
			setup(new NayrusLove(), "nayrus_love"),
			//setup(new Bomb(), "bomb"),
			//setup(new BombWater(), "bomb_water"),
			//setup(new Bombchu(), "bombchu"),
			//setup(new Boomerang(), "boomerang"),
			//setup(new Slingshot(), "slingshot"),
			//setup(new Ocarina(), "ocarina"),
			//setup(new Lens(), "lens"),
			//setup(new MagicBean(), "magic_bean"),
			//setup(new Hookshot(), "hookshot"),
			//setup(new Hookshot(), "longshot"),
			//setup(new MegatronHammer(), "megatron_hammer"),
			setup(new ModItem("Collect four to obtain a new heart", TextFormatting.GOLD, 1), "heart_piece"),
			setup(new ModItem("Increases life capacity by one", TextFormatting.GOLD, 1), "heart_container"),
			setup(new ModItem("Heals the player", TextFormatting.GRAY, 64), "heart"),
			setup(new ModItem("Replenishes mana", TextFormatting.GRAY, 64), "mana"),
			setup(new SwordKokiri(), "sword_kokiri"),
			setup(new SwordMaster(), "sword_master"),
			setup(new SwordBiggoron(), "sword_biggoron"),
			setup(new SwordRazor(), "sword_razor"),
			setup(new SwordGilded(), "sword_gilded"),
			setup(new SwordGreatFairy(), "sword_great_fairy"),
			setup(new ShieldDeku(), "shield_deku"),
			setup(new ShieldHylian(), "shield_hylian"),
			setup(new ShieldMirror(), "shield_mirror")
			/*
			setup(new TunicKokiri(), "tunic_kokiri"), //equips the full armour set
			setup(new TunicZora(), "tunic_zora"),
			setup(new TunicGoron(), "tunic_goron"),
			setup(new TunicMagic(), "tunic_magic"),
			setup(new BootsIron(), "boots_iron"),
			setup(new BootsHover(), "boots_hover")
			*/
		);
	}

	@SubscribeEvent
	public static void onRegisterSoundEvents(RegistryEvent.Register<SoundEvent> event) {
		event.getRegistry().registerAll(
			setup(new SoundEvent(new ResourceLocation(HyruleMod.M_ID, "fairy")), "fairy")
		);
	}

	@SubscribeEvent
	public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(
			EntityType.Builder.<DekuNutEntity>create(DekuNutEntity::new, EntityClassification.MISC).setCustomClientFactory(DekuNutEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(24).setUpdateInterval(60).build("deku_nut").setRegistryName(new ResourceLocation(HyruleMod.M_ID, "deku_nut"))
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