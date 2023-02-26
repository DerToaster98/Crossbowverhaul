package de.dertoaster.crossbowverhaul.item;

import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBolt;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBolt extends AbstractItemBolt {

	public ItemBolt(Properties properties, final IItemTier tier) {
		super(properties, tier);
	}

	@Override
	public AbstractArrowEntity createArrow(World world, ItemStack arrowItem, LivingEntity shooter) {
		ProjectileBolt bolt = new ProjectileBolt(world, shooter, this.tier);
		bolt.setTier(this.tier);
		return bolt;
	}

}
