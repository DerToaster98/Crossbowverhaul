package de.dertoaster.crossbowverhaul.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.renderer.FirstPersonRenderer;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mixin(FirstPersonRenderer.class)
public class MixinItemInHandRenderer {
    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;is(Lnet/minecraft/item/Item;)Z", ordinal = 1))
    private boolean newBool(ItemStack itemStack, Item item) { //Makes the code check if the item is an instance of CrossbowItem, instead of being vanilla's crossbow specifically.
        return (itemStack.getItem() instanceof CrossbowItem);
    }
}
