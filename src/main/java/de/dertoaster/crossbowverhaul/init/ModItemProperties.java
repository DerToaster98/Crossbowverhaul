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
	}

}
