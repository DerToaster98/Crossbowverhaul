package de.dertoaster.crossbowverhaul.item;

import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class ItemBolt extends ArrowItem {

	protected final Tiers tier;

	public ItemBolt(Properties properties, final Tiers tier) {
		super(properties);
		this.tier = tier;
	}

	@Override
	public AbstractArrow createArrow(Level world, ItemStack arrowItem, LivingEntity shooter) {
		ProjectileBolt bolt = new ProjectileBolt(world, shooter);
		bolt.setTier(this.tier);
		return bolt;
	}

	public Tiers getTier() {
		return this.tier;
	}
	
}
