package de.dertoaster.crossbowverhaul.item;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.glfw.GLFW;

import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBolt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public abstract class AbstractItemBolt extends ArrowItem {

	protected final IItemTier tier;

	public AbstractItemBolt(Properties properties, final IItemTier tier) {
		super(properties);
		this.tier = tier;
	}
	
	public abstract AbstractArrowEntity createArrow(World world, ItemStack arrowItem, LivingEntity shooter);

	public int getIdForItemProperties() {
		for(ItemTier etier : ItemTier.values()) {
			if((IItemTier)etier == this.getTier()) {
				return etier.ordinal();
			}
		}
		return ItemTier.values().length;
	}
	
	public IItemTier getTier() {
		return this.tier;
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
		if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) != GLFW.GLFW_PRESS) {
			pTooltip.add(new TranslationTextComponent("item." + this.getRegistryName().getNamespace() + ".tooltip.hold_shift"));
		} else {
			pTooltip.add(new TranslationTextComponent("item." + this.getRegistryName().getNamespace() + ".boltdamagetooltip", ProjectileBolt.getAdditionalDamageOf(this.getTier())));
			pTooltip.add(new TranslationTextComponent("item." + this.getRegistryName().getNamespace() + "." + this.getRegistryName().getPath() + ".tooltip"));
		}
	}
	
}
