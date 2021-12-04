package de.dertoaster.crossbowverhaul.entity.projectile;

import de.dertoaster.crossbowverhaul.init.ModEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion.Mode;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraft.world.World;

public class ProjectileBoltExplosive extends ProjectileBolt {

	public ProjectileBoltExplosive(EntityType<? extends ProjectileBolt> entityType, World world) {
		super(entityType, world);
		this.setBaseDamage(this.getBaseDamage());
	}

	public ProjectileBoltExplosive(World p_i46757_1_, double px, double py, double pz) {
		super(ModEntityTypes.BOLT_EXPLOSIVE.get(), px, py, pz, p_i46757_1_);
		this.setBaseDamage(this.getBaseDamage());
	}

	public ProjectileBoltExplosive(World p_i46758_1_, LivingEntity shooter) {
		super(ModEntityTypes.BOLT_EXPLOSIVE.get(), shooter, p_i46758_1_);
		this.setBaseDamage(this.getBaseDamage());
	}
	
	@Override
	public ItemTier getTier() {
		return ItemTier.NETHERITE;
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if(this.level.isClientSide) {
			this.level.addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
		}
	}
	
	@Override
	protected ItemStack getPickupItem() {
		return ItemStack.EMPTY;
	}
	
	@Override
	protected void onHit(RayTraceResult rayTraceResult) {
		if(!this.level.isClientSide) {
			this.level.explode(this.getOwner(), this.xo, this.yo, this.zo, 3.0F, Mode.DESTROY);
		}
		
		super.onHit(rayTraceResult);
		
		this.remove();
	}
	
	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
