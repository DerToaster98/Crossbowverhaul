package de.dertoaster.crossbowverhaul.entity.projectile;

import javax.annotation.Nullable;

import de.dertoaster.crossbowverhaul.init.ModEntityTypes;
import de.dertoaster.crossbowverhaul.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class ProjectileBolt extends AbstractArrow {
	
	private static final EntityDataAccessor<Integer> ID_TIER = SynchedEntityData.defineId(ProjectileBolt.class, EntityDataSerializers.INT);

	public ProjectileBolt(EntityType<? extends ProjectileBolt> entityType, Level world) {
		super(entityType, world);
		this.setBaseDamage(this.getBaseDamage());
	}

	public ProjectileBolt(Level p_i46757_1_, double px, double py, double pz) {
		this(ModEntityTypes.BOLT.get(), px, py, pz, p_i46757_1_);
		this.setBaseDamage(this.getBaseDamage());
	}
	
	protected ProjectileBolt(EntityType<? extends ProjectileBolt> type, double x, double y, double z, Level w) {
		super(type, x, y, z, w);
	}

	public ProjectileBolt(Level p_i46758_1_, LivingEntity shooter) {
		this(ModEntityTypes.BOLT.get(), shooter, p_i46758_1_);
		this.setBaseDamage(this.getBaseDamage());
	}
	
	protected ProjectileBolt(EntityType<? extends ProjectileBolt> type, LivingEntity shooter, Level world) {
		super(type, shooter, world);
	}
	
	public void setTier(Tier tier) {
		if(this.level.isClientSide()) {
			return;
		}
		int tierID = Tiers.values().length;
		for(Tiers et : Tiers.values()) {
			if((Tier)et == this.getTier()) {
				tierID = et.ordinal();
				break;
			}
		}
		this.entityData.set(ID_TIER, tierID);
		
		//recalculate our basse damage
		this.setBaseDamage(this.getBaseDamage());
	}

	public int getSynchedTierID() {
		return this.entityData.get(ID_TIER);
	}
	
	@Override
	protected ItemStack getPickupItem() {
		Tiers etier = Tiers.WOOD;
		for(Tiers et : Tiers.values()) {
			if((Tier)et == this.getTier()) {
				etier = et;
				break;
			}
		}
		switch (etier) {
		case DIAMOND:
			return ModItems.ITEM_BOLT_DIAMOND.get().getDefaultInstance();
		case GOLD:
			return ModItems.ITEM_BOLT_GOLD.get().getDefaultInstance();
		case IRON:
			return ModItems.ITEM_BOLT_IRON.get().getDefaultInstance();
		case NETHERITE:
			return ModItems.ITEM_BOLT_NETHERITE.get().getDefaultInstance();
		default:
			break;
		}
		return ItemStack.EMPTY;
	}
	
	@Nullable
	public Tier getTier() {
		final int tierIdx = this.entityData.get(ID_TIER);
		if (tierIdx >= Tiers.values().length) {
			return null;
		}
		return Tiers.values()[tierIdx];
	}

	public static double getAdditionalDamageOf(Tier tier) {
		if(tier == (Tier)Tiers.GOLD) {
			return 0.5;
		}
		if(tier == (Tier)Tiers.IRON) {
			return 0.5;
		}
		if(tier == (Tier)Tiers.DIAMOND) {
			return 0.5;
		}
		if(tier == (Tier)Tiers.NETHERITE) {
			return 0.5;
		}
		return tier.getAttackDamageBonus();
	}
	
	@Override
	public void setBaseDamage(double newValue) {
		//DONE: Add the damage value of the tier to it!
		final Tier myTier = this.getTier();
		double addDmg = getAdditionalDamageOf(myTier);
		
		super.setBaseDamage(newValue + addDmg);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ID_TIER, 2);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		
		nbt.putString("bolt_tier", this.getTier().toString());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		
		this.setTier(Tiers.valueOf(nbt.getString("bolt_tier")));
	}
	
	//Why tf do i need this?!
	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
