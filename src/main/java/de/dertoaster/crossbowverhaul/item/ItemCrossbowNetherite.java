package de.dertoaster.crossbowverhaul.item;

import java.util.function.Predicate;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ItemCrossbowNetherite extends ItemCrossbow implements IModifiedCrossbowMethod {

	protected static final Predicate<ItemStack> PREDICATE_BOLTS = (itemstack) -> {
		return itemstack.getItem() instanceof ItemBolt;
	};

	protected static final Predicate<ItemStack> PREDICATE_BOLTS_OR_FIREWORK = PREDICATE_BOLTS.or((itemStack) -> {
		return itemStack.getItem() == Items.FIREWORK_ROCKET;
	});

	@Override
	public Predicate<ItemStack> getSupportedHeldProjectiles() {
		return PREDICATE_BOLTS_OR_FIREWORK;
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return PREDICATE_BOLTS;
	}

	public ItemCrossbowNetherite(Properties properties) {
		super(properties);
	}

	@Override
	public int getUseDuration(ItemStack item) {
		return 2 * super.getUseDuration(item);
	}

	@Override
	public int getDefaultProjectileRange() {
		return 2 * super.getDefaultProjectileRange();
	}

	// Override charging duration
	@Override
	public int getMaxChargeTime() {
		return 2 * super.getMaxChargeTime();
	}

}
