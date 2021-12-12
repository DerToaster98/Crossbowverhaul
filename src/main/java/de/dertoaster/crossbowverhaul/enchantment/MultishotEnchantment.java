package de.dertoaster.crossbowverhaul.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.MultiShotEnchantment;

public class MultishotEnchantment extends MultiShotEnchantment {

	public MultishotEnchantment(Rarity p_i50017_1_, EquipmentSlot... p_i50017_2_) {
		super(p_i50017_1_, p_i50017_2_);
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}

}
