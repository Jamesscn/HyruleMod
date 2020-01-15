package com.jamesscn.hyrule.entity;

import com.jamesscn.hyrule.init.ModEntities;
import com.jamesscn.hyrule.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;

public class DekuNutEntity extends ProjectileItemEntity {

    public DekuNutEntity(EntityType<? extends DekuNutEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public DekuNutEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(ModEntities.deku_nut, x, y, z, worldIn);
    }

    public DekuNutEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(ModEntities.deku_nut, livingEntityIn, worldIn);
    }

    public DekuNutEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {
        super(ModEntities.deku_nut, worldIn);
    }

    @Override
    protected Item func_213885_i() {
        return ModItems.deku_nut;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        AxisAlignedBB axisalignedbb = getBoundingBox().grow(4.0D, 2.0D, 4.0D);
        List<LivingEntity> list = world.getEntitiesWithinAABB(LivingEntity.class, axisalignedbb);
        if (!list.isEmpty()) {
            for(LivingEntity livingEntity : list) {
                if (livingEntity.canBeHitWithPotion()) {
                    double d0 = this.getDistanceSq(livingEntity);
                    if (d0 < 16.0D) {
                        livingEntity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 90, 2));
                        livingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 90, 2));
                        livingEntity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 90, 2));
                    }
                }
            }
        }
        world.createExplosion(this, posX, posY, posZ, 0, Explosion.Mode.NONE);
        if (!world.isRemote) {
            world.setEntityState(this, (byte)3);
            remove();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
