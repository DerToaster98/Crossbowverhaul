package de.dertoaster.crossbowverhaul.item;

import java.util.function.Predicate;

import de.dertoaster.crossbowverhaul.config.CrossbowverhaulConfigHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

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
	
	@Override
	public boolean isEnchantable(ItemStack p_41456_) {
		return this.parentClassIsEnchantable(p_41456_) && CrossbowverhaulConfigHolder.coEnchNetheriteCrossbow.get().booleanValue();
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return this.parentClassIsBookEnchantable(stack, book) && CrossbowverhaulConfigHolder.coEnchNetheriteCrossbow.get().booleanValue();
	}

	public ItemCrossbowNetherite(Properties properties) {
		super(properties);
	}

	@Override
	public int getDefaultProjectileRange() {
		return (int)(CrossbowverhaulConfigHolder.coModNetheriteCrossbowProjectileRange.get().intValue() * (super.getDefaultProjectileRange() / CrossbowverhaulConfigHolder.coModCrossbowProjectileRange.get()));
	}
	
	@Override
	public float getProjectileSpeedModifier() {
		return CrossbowverhaulConfigHolder.coModNetheriteCrossbowProjectileSpeed.get().floatValue() * (super.getProjectileSpeedModifier() / CrossbowverhaulConfigHolder.coModCrossbowProjectileSpeed.get().floatValue());
	}

	// Override charging duration
	@Override
	public int getMaxChargeTime() {
		return CrossbowverhaulConfigHolder.coModNetheriteCrossbowChargeTime.get().intValue();
	}

}
