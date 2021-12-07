package de.dertoaster.crossbowverhaul.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import de.dertoaster.crossbowverhaul.item.IModifiedCrossbowMethod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@Mixin(CrossbowItem.class)
public class MixinCrossbowItem {
	
	//Changed methods:
	// - performShooting: Calls the method of an interface class if the item is an instance of that
    // - getChargeDuration: Same as above
	// - shootProjectile: Same as above
	
	/*
	 * Changed the private static performShooting method to call the interface method if the item of the crossbow implements that
	 */
	@Inject(
		at = @At("HEAD"), 
		method = "performShooting(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;FF)V",
		cancellable = true
	)
	private static void performShooting(
		World world, 
		LivingEntity shooter, 
		Hand hand, 
		ItemStack crossbow, 
		float speed, 
		float divergence, 
		//For void types: Use CallbackInfo!!
		CallbackInfo cir
	) {
		if(crossbow.getItem() instanceof IModifiedCrossbowMethod) {
			((IModifiedCrossbowMethod)crossbow.getItem()).modifiedPerformShooting(world, shooter, hand, crossbow, speed, divergence);
			
			cir.cancel();
		}
	}
	
	@Inject(
		at = @At("HEAD"),
		method = "getChargeDuration(Lnet/minecraft/item/ItemStack;)I",
		cancellable = true
	)
	private static void getChargeDuration(
		ItemStack crossbow, 
		CallbackInfoReturnable<Integer> cir
	) {
		if(crossbow.getItem() instanceof IModifiedCrossbowMethod) {
			int result = ((IModifiedCrossbowMethod)crossbow.getItem()).modifiedGetChargeDuration(crossbow);
			
			cir.setReturnValue(result);
		}
	}
	
	@Inject(
		at = @At("HEAD"),
		method = "shootProjectile(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;FZFFF)V",
		cancellable = true
	)
	private static void shootProjectile(
		World world, 
		LivingEntity shooter, 
		Hand handUsed, 
		ItemStack crossbow, 
		ItemStack projectileStack, 
		float shootSoundPitch, 
		boolean flagProjectileCantBePickedUp, 
		float speed, 
		float divergence, 
		float simulated,
		//For void types: Use CallbackInfo!!
		CallbackInfo cir
	) {
		if(crossbow.getItem() instanceof IModifiedCrossbowMethod) {
			((IModifiedCrossbowMethod)crossbow.getItem()).modifiedShootProjectile(world, shooter, handUsed, crossbow, projectileStack, shootSoundPitch, flagProjectileCantBePickedUp, speed, divergence, simulated);
			
			cir.cancel();
		}
	}
	
	
	
	
}
