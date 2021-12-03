package de.dertoaster.crossbowverhaul.item;

import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBolt;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.world.World;

public class ItemBolt extends ArrowItem {

	protected final ItemTier tier;

	public ItemBolt(Properties properties, final ItemTier tier) {
		super(properties);
		this.tier = tier;
	}

	@Override
	public AbstractArrowEntity createArrow(World world, ItemStack arrowItem, LivingEntity shooter) {
		ProjectileBolt bolt = new ProjectileBolt(world, shooter);
		bolt.setTier(this.tier);
		return bolt;
	}

	public ItemTier getTier() {
		return this.tier;
	}
	
}
