package de.dertoaster.crossbowverhaul.item;

import java.util.Properties;
import java.util.function.Predicate;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ItemCrossbowNetherite extends ItemCrossbow implements IModifiedCrossbowMethod {

	protected static final Predicate<ItemStack> PREDICATE_BOLTS_OR_FIREWORK = PREDICATE_BOLTS_ONLY.or((itemStack) -> {
		return itemStack.getItem() == Items.FIREWORK_ROCKET;
	});

	@Override
	public Predicate<ItemStack> getSupportedHeldProjectiles() {
		return PREDICATE_BOLTS_OR_FIREWORK;
	}
	
	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return PREDICATE_BOLTS_ONLY;
	}

	public ItemCrossbowNetherite(Properties properties) {
		super(properties);
	}

	@Override
	public int getDefaultProjectileRange() {
		return 2 * super.getDefaultProjectileRange();
	}
	
	@Override
	public float getProjectileSpeedModifier() {
		return 2 * super.getProjectileSpeedModifier();
	}

	// Override charging duration
	@Override
	public int getMaxChargeTime() {
		return (int)(1.5D * super.getMaxChargeTime());
	}

}
