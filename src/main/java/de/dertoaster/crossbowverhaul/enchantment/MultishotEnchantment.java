package de.dertoaster.crossbowverhaul.enchantment;

import net.minecraft.inventory.EquipmentSlotType;

public class MultishotEnchantment extends net.minecraft.enchantment.MultishotEnchantment {

	public MultishotEnchantment(Rarity p_i50017_1_, EquipmentSlotType... p_i50017_2_) {
		super(p_i50017_1_, p_i50017_2_);
	}
	
	@Override
	public int getMaxLevel() {
		return CrossbowverhaulConfigHolder.coModMultishot.get();
	}

}
