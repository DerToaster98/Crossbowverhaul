package de.dertoaster.crossbowverhaul.entity.projectile;

import javax.annotation.Nullable;

import de.dertoaster.crossbowverhaul.init.ModEntityTypes;
import de.dertoaster.crossbowverhaul.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
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
	
	public ProjectileBolt(Level world, LivingEntity shooter, Tier tier) {
		this(world, shooter);
		this.setTier(tier);
	}

	public void setTier(Tier tier) {
		int tierID = Tiers.values().length;
		for(Tiers et : Tiers.values()) {
			if(et == (Tiers)tier) {
				tierID = et.ordinal();
				break;
			}
		}
		this.getEntityData().set(ID_TIER, tierID);
		//recalculate our basse damage
		this.setBaseDamage(this.getBaseDamage());
	}

	public int getSynchedTierID() {
		return this.getEntityData().get(ID_TIER);
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

	@Override
	protected ItemStack getDefaultPickupItem() {
		return this.getPickupItem();
	}

	@Nullable
	public Tier getTier() {
		final int tierIdx = this.getSynchedTierID();
		if (tierIdx >= Tiers.values().length) {
			return Tiers.DIAMOND;
		}
		return Tiers.values()[tierIdx];
	}

	public static double getAdditionalDamageOf(Tier tier) {
		if(tier == (Tier)Tiers.GOLD) {
			return 0.5;
		}
		if(tier == (Tier)Tiers.IRON) {
			return 1.0;
		}
		if(tier == (Tier)Tiers.DIAMOND) {
			return 3.0;
		}
		if(tier == (Tier)Tiers.NETHERITE) {
			return 4.0;
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
	protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
		super.defineSynchedData(pBuilder);
		pBuilder.define(ID_TIER, 2);
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
	
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity pEntity) {
		return new ClientboundAddEntityPacket(this, pEntity);
	}

	@Override
	protected void onHitEntity(EntityHitResult pResult) {
		Entity ent = pResult.getEntity();
		if(ent != null) {
			ent.invulnerableTime = 0;
		}
		super.onHitEntity(pResult);
	}

}
