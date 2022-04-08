package de.dertoaster.crossbowverhaul.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class CrossbowverhaulConfigHolder {
	
	private static final boolean defaultAllowEnchantmentsOnCrossbow = true;
	private static final boolean defaultAllowEnchantmentsOnNetheriteCrossbow = true;
	private static final boolean defaultAllowExplosiveBoltsOnNormalCrossbow = false;
	private static final int defaultCrossbowDurability = 512;
	private static final int defaultNetheriteCrossbowDurability = 1024;
	private static final int defaultModifyMultiShotEnchantment = 3;
	private static final double defaultModCrossbowChargeTime = 2.0D;
	private static final double defaultModNetheriteCrossbowChargeTime = 3.0D;
	private static final float defaultModCrossbowProjectileSpeed = 1.0F;
	private static final float defaultModNetheriteCrossbowProjectileSpeed = 1.5F;
	private static final double defaultModCrossbowProjectileRange = 1.0D;
	private static final double defaultModNetheriteCrossbowProjectileRange = 1.5D;
	
	public static final ConfigValue<Boolean> coEnchCrossbow;
	public static final ConfigValue<Boolean> coEnchNetheriteCrossbow;
	public static final ConfigValue<Boolean> coAllowExplosiveBoltsOnNormalCrossbow;
	public static final ConfigValue<Integer> coModMultishot;
	public static final ConfigValue<Integer> coCrossbowDurability;
	public static final ConfigValue<Integer> coNetheriteCrossbowDurability;
	public static final ConfigValue<Double> coModCrossbowChargeTime;
	public static final ConfigValue<Double> coModNetheriteCrossbowChargeTime;
	public static final ConfigValue<Float> coModCrossbowProjectileSpeed;
	public static final ConfigValue<Double> coModCrossbowProjectileRange;
	public static final ConfigValue<Float> coModNetheriteCrossbowProjectileSpeed;
	public static final ConfigValue<Double> coModNetheriteCrossbowProjectileRange;
	
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	static {
		
		BUILDER.push("co-item");
		
			BUILDER.push("requires-game-restart");
		
				coCrossbowDurability = BUILDER
						.worldRestart()
						.defineInRange("Crossbow durability", defaultCrossbowDurability, 1, Integer.MAX_VALUE);
				
				coNetheriteCrossbowDurability = BUILDER
						.worldRestart()
						.defineInRange("Netherite Crossbow durability", defaultNetheriteCrossbowDurability, 1, Integer.MAX_VALUE);
			
			BUILDER.pop();
		
			coEnchCrossbow = BUILDER
					.comment("Enable enchantments on the standard crossbow")
					.define("Enable enchants on crossbow", defaultAllowEnchantmentsOnCrossbow);
			
			coEnchNetheriteCrossbow = BUILDER
					.comment("Enable enchantments on the netherite crossbow")
					.define("Enable enchants on netherite crossbow", defaultAllowEnchantmentsOnNetheriteCrossbow);
			
			coAllowExplosiveBoltsOnNormalCrossbow = BUILDER
					.comment("Allow explosive bolts to be fired from the standard crossbow")
					.define("Explosive bolts on non-netherite crossbow", defaultAllowExplosiveBoltsOnNormalCrossbow);
			
			coModCrossbowChargeTime = BUILDER
					.comment("Multiplier for the standard crossbow's charge time, base charge time is 25 ticks")
					.defineInRange("Crossbow charge-time modifier", defaultModCrossbowChargeTime, 0.0D, Double.MAX_VALUE);
			
			coModNetheriteCrossbowChargeTime = BUILDER
					.comment("Multiplier for the netherite crossbow's charge time, base charge time is 25 ticks")
					.defineInRange("Netherite Crossbow charge-time modifier", defaultModNetheriteCrossbowChargeTime, 0.0D, Double.MAX_VALUE);
			
			coModCrossbowProjectileSpeed = BUILDER
					.comment("Multiplier for the projectile speed for projectiles shot from the standard crossbow")
					.defineInRange("Crossbow projectile speed multiplier", defaultModCrossbowProjectileSpeed, 1.0F, Float.MAX_VALUE, Float.class);
			
			coModNetheriteCrossbowProjectileSpeed = BUILDER
					.comment("Multiplier for the projectile speed for projectiles shot from the netherite crossbow")
					.defineInRange("Netherite Crossbow projectile speed multiplier", defaultModNetheriteCrossbowProjectileSpeed, 1.0F, Float.MAX_VALUE, Float.class);
			
			coModCrossbowProjectileRange = BUILDER
					.comment("Multiplier for the projectile rabge for projectiles shot from the standard crossbow")
					.defineInRange("Crossbow projectile range multiplier", defaultModCrossbowProjectileRange, 1.0F, Double.MAX_VALUE);
			
			coModNetheriteCrossbowProjectileRange = BUILDER
					.comment("Multiplier for the projectile range for projectiles shot from the netherite crossbow")
					.defineInRange("Netherite Crossbow projectile range multiplier", defaultModNetheriteCrossbowProjectileRange, 1.0F, Double.MAX_VALUE);
			
		BUILDER.pop();
		
		BUILDER.push("co-ench");
		
			coModMultishot = BUILDER
					.comment("Maximum level of multishot. Resulting projctiles: (<value> * 2) + 1. For vanilla values, set it to 1")
					.defineInRange("Modify multishot enchantment", defaultModifyMultiShotEnchantment, 1, Integer.MAX_VALUE);
		
		BUILDER.pop();
		
		SPEC = BUILDER.build();
	}
	
}
