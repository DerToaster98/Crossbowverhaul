package de.dertoaster.crossbowverhaul.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.extensions.IItemExtension;

public interface IRegistryNameProvider extends IItemExtension {
	
	public default ResourceLocation getRegistryName() {
		if(this instanceof Item self) {
			return BuiltInRegistries.ITEM.getKey(self);
		}
		return null;
	}

}
