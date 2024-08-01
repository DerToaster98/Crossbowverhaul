package de.dertoaster.crossbowverhaul.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;

public class COConfig {
	
	public static final CrossbowverhaulConfig CONFIG;
	public static final ModConfigSpec CONFIG_SPEC;
	static {
		final Pair<CrossbowverhaulConfig, ModConfigSpec> serverSpecPair = new ModConfigSpec.Builder().configure(CrossbowverhaulConfig::new);
		CONFIG = serverSpecPair.getLeft();
		CONFIG_SPEC = serverSpecPair.getRight();
	}
	
	public static void loadConfig(ModConfigSpec config, String path) {
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave()
				.writingMode(WritingMode.REPLACE).build();
		file.load();
		config.correct(file);
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
		
		public final ModConfigSpec.BooleanValue coEnchCrossbow;
		public final ModConfigSpec.BooleanValue coEnchNetheriteCrossbow;
		public final ModConfigSpec.BooleanValue coAllowExplosiveBoltsOnNormalCrossbow;
		public final ModConfigSpec.IntValue coCrossbowDurability;
		public final ModConfigSpec.IntValue coNetheriteCrossbowDurability;
		public final ModConfigSpec.IntValue coModCrossbowChargeTime;
		public final ModConfigSpec.IntValue coModNetheriteCrossbowChargeTime;
		public final ModConfigSpec.DoubleValue coModCrossbowProjectileSpeed;
		public final ModConfigSpec.DoubleValue coModCrossbowProjectileRange;
		public final ModConfigSpec.DoubleValue coModNetheriteCrossbowProjectileSpeed;
		public final ModConfigSpec.DoubleValue coModNetheriteCrossbowProjectileRange;
		
		CrossbowverhaulConfig(final ModConfigSpec.Builder BUILDER) {
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
		}
	}
	
}
