package de.dertoaster.crossbowverhaul.init;

import de.dertoaster.crossbowverhaul.CrossbowverhaulMod;
import de.dertoaster.crossbowverhaul.item.ItemCrossbow;
import de.dertoaster.crossbowverhaul.item.ItemCrossbowNetherite;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class ModItemProperties {

	public static void register() {
		// And finally, register the model predicates for the crossbow...
		ItemModelsProperties.register(ModItems.ITEM_CROSSBOW.get(), new ResourceLocation(CrossbowverhaulMod.MODID, "bolt_tier"), (itemStack, clientWorld, itemHolder) -> {
			return itemStack != null ? ItemCrossbow.getBoltTier(itemStack) : 0;
		});
		
		//Netherite crossbow
		ItemModelsProperties.register(ModItems.ITEM_CROSSBOW_NETHERITE.get(), new ResourceLocation(CrossbowverhaulMod.MODID, "bolt_tier"), (itemStack, clientWorld, itemHolder) -> {
			if(itemStack.getItem() == ModItems.ITEM_BOLT_EXPLOSIVE.get()) { 
				return 999;
			}
			return itemStack != null ? ItemCrossbow.getBoltTier(itemStack) : 0;
		});
		
		//Copied from vanilla to mimic normal crossbow
		ItemModelsProperties.register(ModItems.ITEM_CROSSBOW_NETHERITE.get(), new ResourceLocation(CrossbowverhaulMod.MODID, "pull"), (p_239427_0_, p_239427_1_, p_239427_2_) -> {
	         if (p_239427_2_ == null) {
	            return 0.0F;
	         } else {
	            return ItemCrossbowNetherite.isCharged(p_239427_0_) ? 0.0F : (float)(p_239427_0_.getUseDuration() - p_239427_2_.getUseItemRemainingTicks()) / (float)ItemCrossbowNetherite.getChargeDuration(p_239427_0_);
	         }
	      });
		ItemModelsProperties.register(ModItems.ITEM_CROSSBOW_NETHERITE.get(), new ResourceLocation(CrossbowverhaulMod.MODID, "pulling"), (p_239426_0_, p_239426_1_, p_239426_2_) -> {
	         return p_239426_2_ != null && p_239426_2_.isUsingItem() && p_239426_2_.getUseItem() == p_239426_0_ && !ItemCrossbowNetherite.isCharged(p_239426_0_) ? 1.0F : 0.0F;
	      });
		ItemModelsProperties.register(ModItems.ITEM_CROSSBOW_NETHERITE.get(), new ResourceLocation(CrossbowverhaulMod.MODID, "charged"), (p_239425_0_, p_239425_1_, p_239425_2_) -> {
	         return p_239425_2_ != null && ItemCrossbowNetherite.isCharged(p_239425_0_) ? 1.0F : 0.0F;
	      });
		ItemModelsProperties.register(ModItems.ITEM_CROSSBOW_NETHERITE.get(), new ResourceLocation(CrossbowverhaulMod.MODID, "firework"), (p_239424_0_, p_239424_1_, p_239424_2_) -> {
	         return p_239424_2_ != null && ItemCrossbowNetherite.isCharged(p_239424_0_) && ItemCrossbowNetherite.containsChargedProjectile(p_239424_0_, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
	      });
	}

}
