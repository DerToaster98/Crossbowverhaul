package de.dertoaster.crossbowverhaul.item;

import java.util.List;

import de.dertoaster.crossbowverhaul.mixin.CrossbowItemInvoker;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ICrossbowUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

public interface IModifiedCrossbowMethod {
	
	public default float getMultiShotAngle() {
		//Default value is 10 degrees resulting in a opening angle of 20 degrees
		return 22.5F;
	}
	
	public default void modifiedPerformShooting(World world, LivingEntity shooter, Hand handUsed, ItemStack crossbow, float speed, float divergence) {
		 List<ItemStack> list = CrossbowItem.getChargedProjectiles(crossbow);
	      	float[] afloat = CrossbowItem.getShotPitches(shooter.getRandom());

	      	//DONE: Adjust to not be hardcoded to indexes
		//TODO: Check if the implemenation of getShotPitches() works for us => adjust it: 0 is no longer the central projectile but rather the most left projectile!!
		
		//Multishot defines additional projectiles per side
		final float anglePerIteration = this.getMultiShotAngle() / ((float)(list.size() -1) / 2.0F); //Divide by the multishot level: 
		float currentAngle = -this.getMultiShotAngle();
		
	     	for(int i = 0; i < list.size(); ++i) {
	       		ItemStack itemstack = list.get(i);
	         	boolean flag = shooter instanceof PlayerEntity && ((PlayerEntity)shooter).abilities.instabuild;
	         	if (!itemstack.isEmpty()) {
				CrossbowItemInvoker.invokeShootProjectile(world, shooter, handUsed, crossbow, itemstack, afloat[i], flag, speed, divergence, currentAngle);
	       	  	}
	      		currentAngle += anglePerIteration;
	      	}
		CrossbowItemInvoker.invokeOnCrossbowShot(world, shooter, crossbow);
	}
	
	public default void modifiedShootProjectile(World world, LivingEntity shooter, Hand handUsed, ItemStack crossbow, ItemStack projectileStack, float shootSoundPitch, boolean flagProjectileCantBePickedUp, float speed, float divergence, float simulated) {
		 if (!world.isClientSide) {
	         boolean flag = projectileStack.getItem() == Items.FIREWORK_ROCKET;
	         ProjectileEntity projectileentity;
	         if (flag) {
	            projectileentity = new FireworkRocketEntity(world, projectileStack, shooter, shooter.getX(), shooter.getEyeY() - (double)0.15F, shooter.getZ(), true);
	         } else {
	            projectileentity = CrossbowItem.getArrow(world, shooter, crossbow, projectileStack);
	            if (flagProjectileCantBePickedUp || simulated != 0.0F) {
	               ((AbstractArrowEntity)projectileentity).pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
	            }
	         }

	         if (shooter instanceof ICrossbowUser) {
	            ICrossbowUser icrossbowuser = (ICrossbowUser)shooter;
	            icrossbowuser.shootCrossbowProjectile(icrossbowuser.getTarget(), crossbow, projectileentity, simulated);
	         } else {
	            Vector3d vector3d1 = shooter.getUpVector(1.0F);
	            Quaternion quaternion = new Quaternion(new Vector3f(vector3d1), simulated, true);
	            Vector3d vector3d = shooter.getViewVector(1.0F);
	            Vector3f vector3f = new Vector3f(vector3d);
	            vector3f.transform(quaternion);
	            projectileentity.shoot((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), speed * this.getProjectileSpeedModifier(), divergence);
	         }

	         crossbow.hurtAndBreak(flag ? 3 : 1, shooter, (shooterTmp) -> {
	            shooterTmp.broadcastBreakEvent(handUsed);
	         });
	         world.addFreshEntity(projectileentity);
	         world.playSound((PlayerEntity)null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, shootSoundPitch);
	      }
	}

	public default int modifiedGetChargeDuration(ItemStack crossbow) {
		 int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, crossbow);
	     return i == 0 ? this.getMaxChargeTime() : this.getMaxChargeTime() - this.getChargeTimeReductionPerQuickChargeLevel() * i;
	}
	
	default int getMaxChargeTime() {
		return 25;
	}
	
	default int getChargeTimeReductionPerQuickChargeLevel() {
		return 5;
	}
	
	default float getProjectileSpeedModifier() {
		return 1.0F;
	}
}
