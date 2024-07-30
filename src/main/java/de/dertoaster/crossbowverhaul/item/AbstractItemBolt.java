package de.dertoaster.crossbowverhaul.item;

import java.util.List;

import org.lwjgl.glfw.GLFW;

import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBolt;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public abstract class AbstractItemBolt extends ArrowItem implements IRegistryNameProvider {

	protected final Tier tier;

	public AbstractItemBolt(Properties properties, final Tier tier) {
		super(properties);
		this.tier = tier;
	}
	
	public abstract AbstractArrow createArrow(Level world, ItemStack arrowItem, LivingEntity shooter);

	public int getIdForItemProperties() {
		for(Tiers etier : Tiers.values()) {
			if((Tier)etier == this.getTier()) {
				return etier.ordinal();
			}
		}
		return Tiers.values().length;
	}
	
	public Tier getTier() {
		return this.tier;
	}

	@Override
	public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
		super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
		if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) != GLFW.GLFW_PRESS) {
			pTooltipComponents.add(Component.translatable("item." + this.getRegistryName().getNamespace() + ".tooltip.hold_shift"));
		} else {
			pTooltipComponents.add(Component.translatable("item." + this.getRegistryName().getNamespace() + ".boltdamagetooltip", ProjectileBolt.getAdditionalDamageOf(this.getTier())));
			pTooltipComponents.add(Component.translatable("item." + this.getRegistryName().getNamespace() + "." + this.getRegistryName().getPath() + ".tooltip"));
		}
	}
	
}
