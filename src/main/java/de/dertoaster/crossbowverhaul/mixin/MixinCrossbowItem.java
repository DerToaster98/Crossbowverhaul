package de.dertoaster.crossbowverhaul.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import de.dertoaster.crossbowverhaul.item.IModifiedCrossbowMethod;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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
		method = "performShooting(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;FF)V",
		cancellable = true
	)
	private static void performShooting(
		Level world, 
		LivingEntity shooter, 
		InteractionHand hand, 
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
		method = "getChargeDuration(Lnet/minecraft/world/item/ItemStack;)I",
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
		method = "shootProjectile(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;FZFFF)V",
		cancellable = true
	)
	private static void shootProjectile(
		Level world, 
		LivingEntity shooter, 
		InteractionHand handUsed, 
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
