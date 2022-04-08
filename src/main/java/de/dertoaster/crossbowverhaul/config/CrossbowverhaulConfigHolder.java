package de.dertoaster.crossbowverhaul.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class CrossbowverhaulConfigHolder {
	
	private static final boolean allowEnchantmentsOnCrossbow = true;
	private static final boolean allowEnchantmentsOnNetheriteCrossbow = true;
	private static final int modifyMultiShotEnchantment = 3;
	
	public static final ConfigValue<Boolean> coEnchCrossbow;
	public static final ConfigValue<Boolean> coEnchNetheriteCrossbow;
	public static final ConfigValue<Integer> coModMultishot;
	
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	static {
		
		BUILDER.push("co-item");
		
			coEnchCrossbow = BUILDER
					.comment("Enable enchantments on the standard crossbow")
					.define("Enable enchants on crossbow", allowEnchantmentsOnCrossbow);
			
			coEnchNetheriteCrossbow = BUILDER
					.comment("Enable enchantments on the netherite crossbow")
					.define("Enable enchants on netherite crossbow", allowEnchantmentsOnNetheriteCrossbow);
			
		BUILDER.pop();
		
		BUILDER.push("co-ench");
		
			coModMultishot = BUILDER
					.comment("Maximum level of multishot. Resulting projctiles: (<value> * 2) + 1. For vanilla values, set it to 1")
					.defineInRange("Modify multishot enchantment", modifyMultiShotEnchantment, 1, Integer.MAX_VALUE);
		
		BUILDER.pop();
		
		SPEC = BUILDER.build();
	}
	
}
