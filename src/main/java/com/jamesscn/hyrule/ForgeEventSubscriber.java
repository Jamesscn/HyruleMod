package com.jamesscn.hyrule;

import com.jamesscn.hyrule.capabilities.IStatus;
import com.jamesscn.hyrule.capabilities.StatusHandler;
import com.jamesscn.hyrule.capabilities.StatusProvider;
import com.jamesscn.hyrule.capabilities.StatusStorage;
import com.jamesscn.hyrule.entity.DekuNutEntity;
import com.jamesscn.hyrule.init.ModItems;
import com.jamesscn.hyrule.item.Rupee;
import com.jamesscn.hyrule.render.DekuNutRender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.concurrent.atomic.AtomicInteger;

@Mod.EventBusSubscriber(modid = HyruleMod.M_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {

    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event) {
        Item item = event.getItem().getItem().getItem();
        if(item instanceof Rupee) {
            Rupee rupee = (Rupee)item;
            event.getPlayer().getCapability(StatusProvider.statusCapability).ifPresent(status -> {
                status.addRupees(rupee.value * event.getItem().getItem().getCount());
                HyruleMod.print("Rupees: " + status.getRupees());
            });
            event.getItem().remove();
            event.setCanceled(true);
        } else if (item == ModItems.heart_piece) {
            event.getPlayer().getCapability(StatusProvider.statusCapability).ifPresent(status -> {
                status.addHeartPiece();
            });
            event.getPlayer().setHealth(event.getPlayer().getMaxHealth());
            event.getItem().remove();
            event.setCanceled(true);
        } else if (item == ModItems.heart_container) {
            event.getPlayer().getCapability(StatusProvider.statusCapability).ifPresent(status -> {
                status.addHeartContainer();
            });
            event.getPlayer().setHealth(event.getPlayer().getMaxHealth());
            event.getItem().remove();
            event.setCanceled(true);
        } else if (item == ModItems.mana) {
            event.getPlayer().getCapability(StatusProvider.statusCapability).ifPresent(status -> {
                status.addMana(50);
            });
            event.getItem().remove();
            event.setCanceled(true);
        } else if (item == ModItems.heart) {
            event.getPlayer().heal(2);
            event.getItem().remove();
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if(event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)event.getEntityLiving();
            ItemStack fairyBottle = new ItemStack(ModItems.bottle_fairy);
            if(player.inventory.hasItemStack(fairyBottle)) {
                event.setCanceled(true);
                player.setHealth(player.getMaxHealth());
                //player.world.playSound(player, player.posX, player.posY, player.posZ, ModSounds.fairy, SoundCategory.NEUTRAL, 0.5F, 1.0F);
                if(!player.abilities.isCreativeMode) {
                    player.replaceItemInInventory(player.inventory.getSlotFor(fairyBottle), new ItemStack(Items.GLASS_BOTTLE));
                }
            } else {
                AtomicInteger rupeeCount = new AtomicInteger();
                player.getCapability(StatusProvider.statusCapability).ifPresent(status -> {
                    status.setMana(status.getManaLimit() / 2);
                    rupeeCount.set(status.getRupees());
                    status.setRupees(0);
                });
                int rupees = rupeeCount.get();
                while(rupees > 0) {
                    Item rupee;
                    if(rupees >= 200) {
                        rupee = ModItems.rupee_silver;
                        rupees -= 200;
                    } else if (rupees >= 100) {
                        rupee = ModItems.rupee_orange;
                        rupees -= 100;
                    } else if (rupees >= 50) {
                        rupee = ModItems.rupee_purple;
                        rupees -= 50;
                    } else if (rupees >= 20) {
                        rupee = ModItems.rupee_red;
                        rupees -= 20;
                    } else if (rupees >= 10) {
                        rupee = ModItems.rupee_yellow;
                        rupees -= 10;
                    } else if (rupees >= 5) {
                        rupee = ModItems.rupee_blue;
                        rupees -= 5;
                    } else {
                        rupee = ModItems.rupee_green;
                        rupees--;
                    }
                    ItemStack rupeeStack = new ItemStack(rupee);
                    player.dropItem(rupeeStack, true, true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if(event.getTarget() instanceof CowEntity && (event.getItemStack().getItem() == Items.GLASS_BOTTLE || event.getItemStack().getItem() == ModItems.bottle_milk_half)) {
            if (!event.getWorld().isRemote()) {
                event.getItemStack().shrink(1);
                event.getPlayer().addItemStackToInventory(new ItemStack(ModItems.bottle_milk));
            }
        } else if(event.getTarget() instanceof LivingEntity && event.getItemStack().getItem() == ModItems.deku_stick && event.getItemStack().getDamage() == 1) {
            event.getTarget().setFire(6);
        }
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if(event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)event.getEntityLiving();
            if(event.getSource().isFireDamage()) {
                if(player.getHeldItemMainhand().getItem() == ModItems.shield_deku) {
                    player.getHeldItemMainhand().shrink(1);
                }
                if(player.getHeldItemOffhand().getItem() == ModItems.shield_deku) {
                    player.getHeldItemOffhand().shrink(1);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        CapabilityManager.INSTANCE.register(IStatus.class, new StatusStorage(), StatusHandler::new);
        RenderingRegistry.registerEntityRenderingHandler(DekuNutEntity.class, DekuNutRender::new);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(HyruleMod.M_ID, "status"), new StatusProvider());
        }
    }

    @SubscribeEvent
    public static void clonePlayer(PlayerEvent.Clone event) {
        AtomicInteger mana = new AtomicInteger();
        AtomicInteger manaLimit = new AtomicInteger();
        AtomicInteger rupees = new AtomicInteger();
        AtomicInteger rupeeLimit = new AtomicInteger();
        AtomicInteger heartPieces = new AtomicInteger();
        AtomicInteger heartContainers = new AtomicInteger();
        event.getOriginal().getCapability(StatusProvider.statusCapability).ifPresent(status -> {
            mana.set(status.getMana());
            manaLimit.set(status.getManaLimit());
            rupees.set(status.getRupees());
            rupeeLimit.set(status.getRupeeLimit());
            heartPieces.set(status.getHeartPieces());
            heartContainers.set(status.getHeartContainers());
        });
        event.getPlayer().getCapability(StatusProvider.statusCapability).ifPresent(status -> {
            status.setManaLimit(manaLimit.get());
            status.setMana(mana.get());
            status.setRupeeLimit(rupeeLimit.get());
            status.setRupees(rupees.get());
            status.setHeartPieces(heartPieces.get());
            status.setHeartContainers(heartContainers.get());
        });
    }
}
