package de.dertoaster.crossbowverhaul.item;

import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBolt;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class ItemBolt extends AbstractItemBolt {

	public ItemBolt(Properties properties, final Tier tier) {
		super(properties, tier);
	}

	@Override
	public AbstractArrowEntity createArrow(World world, ItemStack arrowItem, LivingEntity shooter) {
		ProjectileBolt bolt = new ProjectileBolt(world, shooter, this.tier);
		bolt.setTier(this.tier);
		return bolt;
	}

}
