package de.dertoaster.crossbowverhaul.init;

import de.dertoaster.crossbowverhaul.CrossbowverhaulMod;
import de.dertoaster.crossbowverhaul.item.ItemBoltExplosive;
import de.dertoaster.crossbowverhaul.item.ItemCrossbow;
import de.dertoaster.crossbowverhaul.item.ItemCrossbowNetherite;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.component.ChargedProjectiles;

public class ModItemProperties {

	public static void register() {
		// And finally, register the model predicates for the crossbow...
		ItemProperties.register(ModItems.ITEM_CROSSBOW.get(), ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "bolt_tier"), (itemStack, clientWorld, itemHolder, intIn) -> {
			if(ItemCrossbow.getFirstLoadedBolt(itemStack).getItem() instanceof ItemBoltExplosive) { 
				return Tiers.values().length;
			}
			return itemStack != null ? ItemCrossbow.getBoltTier(itemStack) : 0;
		});
		
		//Netherite crossbow
		ItemProperties.register(ModItems.ITEM_CROSSBOW_NETHERITE.get(), ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "bolt_tier"), (itemStack, clientWorld, itemHolder, intIn) -> {
			if(ItemCrossbow.getFirstLoadedBolt(itemStack).getItem() instanceof ItemBoltExplosive) { 
				return Tiers.values().length;
			}
			return itemStack != null ? ItemCrossbow.getBoltTier(itemStack) : 0;
		});
		
		//Copied from vanilla to mimic normal crossbow
		ItemProperties.register(ModItems.ITEM_CROSSBOW_NETHERITE.get(), ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "pull"), (itemstack, clientLevel, entity, intIn) -> {
			if (entity == null) {
				return 0.0F;
			} else {
				return ItemCrossbowNetherite.isCharged(itemstack)
						? 0.0F
						: (float)(itemstack.getUseDuration(entity) - entity.getUseItemRemainingTicks())
						/ (float)ItemCrossbowNetherite.getChargeDuration(itemstack, entity);
			}
	      });
		ItemProperties.register(ModItems.ITEM_CROSSBOW_NETHERITE.get(), ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "pulling"), (itemstack, clientLevel, entity, intIn) -> entity != null
                && entity.isUsingItem()
                && entity.getUseItem() == itemstack
                && !ItemCrossbowNetherite.isCharged(itemstack)
                ? 1.0F
                : 0.0F);
		ItemProperties.register(ModItems.ITEM_CROSSBOW_NETHERITE.get(), ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "charged"), (itemstack, clientLevel, entity, intIn) -> ItemCrossbowNetherite.isCharged(itemstack) ? 1.0F : 0.0F);
		ItemProperties.register(ModItems.ITEM_CROSSBOW_NETHERITE.get(), ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "firework"), (itemstack, clientLevel, entity, intIn) -> {
			ChargedProjectiles chargedprojectiles = itemstack.get(DataComponents.CHARGED_PROJECTILES);
			return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
	      });
	}

}
