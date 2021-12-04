package de.dertoaster.crossbowverhaul.init;

import de.dertoaster.crossbowverhaul.item.ItemCrossbow;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;

public class ModItemProperties {

	public static void register() {
		// And finally, register the model predicates for the crossbow...
		ItemModelsProperties.register(ModItems.ITEM_CROSSBOW.get(), new ResourceLocation("bolt_tier"), (itemStack, clientWorld, itemHolder) -> {
			return itemStack != null ? ItemCrossbow.getBoltTier(itemStack) : 0;
		});
		
		//Netherite crossbow
		ItemModelsProperties.register(ModItems.ITEM_CROSSBOW_NETHERITE.get(), new ResourceLocation("bolt_tier"), (itemStack, clientWorld, itemHolder) -> {
			if(itemStack.getItem() == ModItems.ITEM_BOLT_EXPLOSIVE.get()) { 
				return -1;
			}
			return itemStack != null ? ItemCrossbow.getBoltTier(itemStack) : 0;
		});
	}

}
