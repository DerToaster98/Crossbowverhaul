package de.dertoaster.crossbowverhaul.config;

import java.io.File;

import org.apache.commons.lang3.tuple.Pair;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class COConfig {
	
	public static final CrossbowverhaulConfig CONFIG;
	public static final ForgeConfigSpec CONFIG_SPEC;
	static {
		final Pair<CrossbowverhaulConfig, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder().configure(CrossbowverhaulConfig::new);
		CONFIG = serverSpecPair.getLeft();
		CONFIG_SPEC = serverSpecPair.getRight();
	}
	
	public static void loadConfig(ForgeConfigSpec config, String path) {
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave()
				.writingMode(WritingMode.REPLACE).build();
		file.load();
		config.setConfig(file);
	}

	public static class CrossbowverhaulConfig {
		
		private static final boolean defaultAllowEnchantmentsOnCrossbow = true;
		private static final boolean defaultAllowEnchantmentsOnNetheriteCrossbow = true;
		private static final boolean defaultAllowExplosiveBoltsOnNormalCrossbow = false;
		private static final int defaultCrossbowDurability = 512;
		private static final int defaultNetheriteCrossbowDurability = 1024;
		private static final int defaultModifyMultiShotEnchantment = 3;
		private static final Integer defaultModCrossbowChargeTime = 50;
		private static final Integer defaultModNetheriteCrossbowChargeTime = 75;
		private static final double defaultModCrossbowProjectileSpeed = 1.0F;
		private static final double defaultModNetheriteCrossbowProjectileSpeed = 1.5F;
		private static final double defaultModCrossbowProjectileRange = 1.0D;
		private static final double defaultModNetheriteCrossbowProjectileRange = 1.5D;
		
		public final ConfigValue<Boolean> coEnchCrossbow;
		public final ConfigValue<Boolean> coEnchNetheriteCrossbow;
		public final ConfigValue<Boolean> coAllowExplosiveBoltsOnNormalCrossbow;
		public final ConfigValue<Integer> coModMultishot;
		public final ConfigValue<Integer> coCrossbowDurability;
		public final ConfigValue<Integer> coNetheriteCrossbowDurability;
		public final ConfigValue<Integer> coModCrossbowChargeTime;
		public final ConfigValue<Integer> coModNetheriteCrossbowChargeTime;
		public final ConfigValue<Double> coModCrossbowProjectileSpeed;
		public final ConfigValue<Double> coModCrossbowProjectileRange;
		public final ConfigValue<Double> coModNetheriteCrossbowProjectileSpeed;
		public final ConfigValue<Double> coModNetheriteCrossbowProjectileRange;
		
		CrossbowverhaulConfig(final ForgeConfigSpec.Builder BUILDER) {
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
						.comment("Standard crossbow charge time, vanilla charge time is 25 ticks")
						.defineInRange("Crossbow charge-time modifier", defaultModCrossbowChargeTime, 1, Integer.MAX_VALUE);
				
				coModNetheriteCrossbowChargeTime = BUILDER
						.comment("Netherite crossbow charge time, vanilla charge time is 25 ticks")
						.defineInRange("Netherite Crossbow charge-time modifier", defaultModNetheriteCrossbowChargeTime, 1, Integer.MAX_VALUE);
				
				coModCrossbowProjectileSpeed = BUILDER
						.comment("Multiplier for the projectile speed for projectiles shot from the standard crossbow")
						.defineInRange("Crossbow projectile speed multiplier", defaultModCrossbowProjectileSpeed, 1.0D, Float.MAX_VALUE);
				
				coModNetheriteCrossbowProjectileSpeed = BUILDER
						.comment("Multiplier for the projectile speed for projectiles shot from the netherite crossbow")
						.defineInRange("Netherite Crossbow projectile speed multiplier", defaultModNetheriteCrossbowProjectileSpeed, 1.0D, Float.MAX_VALUE);
				
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
		}
	}
	
}
