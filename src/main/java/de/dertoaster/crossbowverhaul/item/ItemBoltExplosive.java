package de.dertoaster.crossbowverhaul.item;

import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBoltExplosive;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.world.World;

public class ItemBoltExplosive extends ItemBolt {

	public ItemBoltExplosive(Properties properties) {
		super(properties, ItemTier.NETHERITE);
	}
	
	@Override
	public AbstractArrowEntity createArrow(World world, ItemStack arrowItem, LivingEntity shooter) {
		ProjectileBoltExplosive bolt = new ProjectileBoltExplosive(world, shooter);
		return bolt;
	}

}
