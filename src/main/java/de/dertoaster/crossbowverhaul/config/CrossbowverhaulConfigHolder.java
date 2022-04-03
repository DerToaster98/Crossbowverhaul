package de.dertoaster.crossbowverhaul.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class CrossbowverhaulConfigHolder {
	
	private static final boolean allowEnchantmentsOnCrossbow = true;
	private static final boolean allowEnchantmentsOnNetheriteCrossbow = true;
	private static final boolean modifyMultiShotEnchantment = true;
	
	public static final ConfigValue<Boolean> coEnchCrossbow;
	public static final ConfigValue<Boolean> coEnchNetheriteCrossbow;
	public static final ConfigValue<Boolean> coModMultishot;
	
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	static {
		
		BUILDER.push("co-item");
		
			coEnchCrossbow = BUILDER
					.comment("Enable enchantments on the crossbow")
					.define("Enable enchants on crossbow", allowEnchantmentsOnCrossbow);
			
			coEnchNetheriteCrossbow = BUILDER
					.comment("Enable enchantments on the netherite crossbow")
					.define("Enable enchants on netherite crossbow", allowEnchantmentsOnNetheriteCrossbow);
			
		BUILDER.pop();
		
		BUILDER.push("co-ench");
		
			coModMultishot = BUILDER
					.comment("Allows multishot to go up to five")
					.define("Modify multishot enchantment", modifyMultiShotEnchantment);
		
		BUILDER.pop();
		
		SPEC = BUILDER.build();
	}
	
}
