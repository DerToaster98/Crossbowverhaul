package de.dertoaster.crossbowverhaul.item;

import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBoltExplosive;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class ItemBoltExplosive extends ItemBolt {

	public ItemBoltExplosive(Properties properties) {
		super(properties, Tiers.NETHERITE);
	}
	
	@Override
	public AbstractArrow createArrow(Level world, ItemStack arrowItem, LivingEntity shooter) {
		ProjectileBoltExplosive bolt = new ProjectileBoltExplosive(world, shooter);
		return bolt;
	}

}
