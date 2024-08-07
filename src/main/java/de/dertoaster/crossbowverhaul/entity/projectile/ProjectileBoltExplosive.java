package de.dertoaster.crossbowverhaul.entity.projectile;

import de.dertoaster.crossbowverhaul.init.ModEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.HitResult;

public class ProjectileBoltExplosive extends ProjectileBolt {

	public ProjectileBoltExplosive(EntityType<? extends ProjectileBolt> entityType, Level world) {
		super(entityType, world);
		this.setBaseDamage(this.getBaseDamage());
	}

	public ProjectileBoltExplosive(Level p_i46757_1_, double px, double py, double pz) {
		super(ModEntityTypes.BOLT_EXPLOSIVE.get(), px, py, pz, p_i46757_1_);
		this.setBaseDamage(this.getBaseDamage());
	}

	public ProjectileBoltExplosive(Level p_i46758_1_, LivingEntity shooter) {
		super(ModEntityTypes.BOLT_EXPLOSIVE.get(), shooter, p_i46758_1_);
		this.setBaseDamage(this.getBaseDamage());
	}
	
	@Override
	public Tiers getTier() {
		return Tiers.NETHERITE;
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if(this.level().isClientSide) {
			this.level().addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
		}
	}
	
	@Override
	protected ItemStack getPickupItem() {
		return ItemStack.EMPTY;
	}
	
	@Override
	protected void onHit(HitResult rayTraceResult) {
		if(!this.level().isClientSide) {
			this.level().explode(this.getOwner(), this.xo, this.yo, this.zo, 3.0F, ExplosionInteraction.BLOCK);
		}
		
		super.onHit(rayTraceResult);
		
		this.remove(RemovalReason.KILLED);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity pEntity) {
		return new ClientboundAddEntityPacket(this, pEntity);
	}

}
