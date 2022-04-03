package de.dertoaster.crossbowverhaul.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class CrossbowverhaulConfigHolder {

	public static class ItemConfig {
		private static final boolean allowEnchantmentsOnCrossbow = true;
		private static final boolean allowEnchantmentsOnNetheriteCrossbow = true;
		private static final boolean modifyMultiShotEnchantment = true;
		
		public final ConfigValue<Boolean> coEnchCrossbow;
		public final ConfigValue<Boolean> coEnchNetheriteCrossbow;
		public final ConfigValue<Boolean> coModMultishot;
		
		public ItemConfig(ForgeConfigSpec.Builder builder) {
			builder.push("co-item");
			
				this.coEnchCrossbow = builder
						.comment("Enable enchantments on the crossbow")
						.defineInRange("Enable enchants on crossbow", allowEnchantmentsOnCrossbow, true, false, Boolean.class);
				
				this.coEnchNetheriteCrossbow = builder
						.comment("Enable enchantments on the netherite crossbow")
						.defineInRange("Enable enchants on netherite crossbow", allowEnchantmentsOnNetheriteCrossbow, true, false, Boolean.class);
				
			builder.pop();
			
			builder.push("co-ench");
			
				this.coModMultishot = builder
						.comment("Allows multishot to go up to five")
						.defineInRange("Modify multishot enchantment", modifyMultiShotEnchantment, true, false, Boolean.class);
			
			builder.pop();
		}
		
	}
	
	public static final ItemConfig ITEM_CONFIG;
	public static final ForgeConfigSpec ITEM_CONFIG_SPEC;
	
	static {
		Pair<ItemConfig, ForgeConfigSpec> itemConfigSpecPair = new ForgeConfigSpec.Builder().configure(ItemConfig::new);
		
		ITEM_CONFIG = itemConfigSpecPair.getLeft();
		ITEM_CONFIG_SPEC = itemConfigSpecPair.getRight();
	}
	
}
