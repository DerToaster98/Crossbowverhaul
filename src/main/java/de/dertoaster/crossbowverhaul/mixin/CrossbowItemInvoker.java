package de.dertoaster.crossbowverhaul.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@Mixin(CrossbowItem.class)
public interface CrossbowItemInvoker {

	@Invoker("shootProjectile")
	public static void invokeShootProjectile(World world, LivingEntity shooter, Hand handUsed, ItemStack crossbow, ItemStack projectileStack, float shootSoundPitch, boolean flagProjectileCantBePickedUp, float speed, float divergence, float simulated) {
		throw new AssertionError();
	}
	
	@Invoker("onCrossbowShot")
	public static void invokeOnCrossbowShot(World world, LivingEntity shooter, ItemStack crossbow) {
		throw new AssertionError();
	}
	
	@Invoker("addChargedProjectile")
	public static void invokeAddChargedProjectile(ItemStack crossbow, ItemStack projectile) {
		throw new AssertionError();
	}
	
}
