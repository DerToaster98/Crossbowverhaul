package de.dertoaster.crossbowverhaul.entity.projectile;

import javax.annotation.Nullable;

import de.dertoaster.crossbowverhaul.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ProjectileBolt extends AbstractArrowEntity {
	
	private static final DataParameter<Integer> ID_TIER = EntityDataManager.defineId(ProjectileBolt.class, DataSerializers.INT);

	public ProjectileBolt(EntityType<? extends ProjectileBolt> entityType, World world) {
		super(entityType, world);
		this.setBaseDamage(this.getBaseDamage());
	}

	public ProjectileBolt(World p_i46757_1_, double px, double py, double pz) {
		super(EntityType.ARROW, px, py, pz, p_i46757_1_);
		this.setBaseDamage(this.getBaseDamage());
	}

	public ProjectileBolt(World p_i46758_1_, LivingEntity shooter) {
		super(EntityType.ARROW, shooter, p_i46758_1_);
		this.setBaseDamage(this.getBaseDamage());
	}
	
	public void setTier(ItemTier tier) {
		if(this.level.isClientSide()) {
			return;
		}
		this.entityData.set(ID_TIER, tier.ordinal());
		
		//recalculate our basse damage
		this.setBaseDamage(this.getBaseDamage());
	}

	@Override
	protected ItemStack getPickupItem() {
		switch (this.getTier()) {
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
	public ItemTier getTier() {
		final int tierIdx = this.entityData.get(ID_TIER);
		if (tierIdx >= ItemTier.values().length) {
			return null;
		}
		return ItemTier.values()[tierIdx];
	}

	@Override
	public void setBaseDamage(double newValue) {
		//DONE: Add the damage value of the tier to it!
		final ItemTier myTier = this.getTier();
		double addDmg = 0;
		switch(myTier) {
		case GOLD:
			addDmg = 0.5;
			break;
		case IRON:
			addDmg = 1.0;
			break;
		case DIAMOND:
			addDmg = 3.0;
			break;
		case NETHERITE:
			addDmg = 4.0;
			break;
		default:
			break;
		
		}
		super.setBaseDamage(newValue + addDmg);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ID_TIER, 2);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) {
		super.addAdditionalSaveData(nbt);
		
		nbt.putString("bolt_tier", this.getTier().toString());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) {
		super.readAdditionalSaveData(nbt);
		
		this.setTier(ItemTier.valueOf(nbt.getString("bolt_tier")));
	}
	
	//Why tf do i need this?!
	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
