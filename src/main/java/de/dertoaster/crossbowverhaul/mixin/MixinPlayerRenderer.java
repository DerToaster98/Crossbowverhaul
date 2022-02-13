package de.dertoaster.crossbowverhaul.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;

@Mixin(PlayerRenderer.class)
public class MixinPlayerRenderer {

	@Inject(
			at = @At("HEAD"), 
			method = "getArmPose(Lnet/minecraft/client/player/AbstractClientPlayer;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel/ArmPose;", 
			cancellable = true
	)
	private static void getArmPose(AbstractClientPlayer player, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!itemstack.isEmpty()) {
			if (player.getUsedItemHand() == hand && player.getUseItemRemainingTicks() > 0) {
				
			} else  if (!player.swinging && itemstack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(itemstack)) {
				cir.setReturnValue(ArmPose.CROSSBOW_HOLD);
			}
		}
	}

}
