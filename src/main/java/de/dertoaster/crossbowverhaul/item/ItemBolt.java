package de.dertoaster.crossbowverhaul.item;

import java.util.List;

import org.lwjgl.glfw.GLFW;

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
	
	@Override
	public void appendHoverText(ItemStack p_77624_1_, Level p_77624_2_, List<Component> tooltip, TooltipFlag p_77624_4_) {
		super.appendHoverText(p_77624_1_, p_77624_2_, tooltip, p_77624_4_);
		if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) != GLFW.GLFW_PRESS) {
			tooltip.add(new TranslatableComponent("item." + this.getRegistryName().getNamespace() + ".tooltip.hold_shift"));
		} else {
			tooltip.add(new TranslatableComponent("item." + this.getRegistryName().getNamespace() + ".boltdamagetooltip", ProjectileBolt.getAdditionalDamageOf(this.getTier())));
			tooltip.add(new TranslatableComponent("item." + this.getRegistryName().getNamespace() + "." + this.getRegistryName().getPath() + ".tooltip"));
		}
	}
	
}
