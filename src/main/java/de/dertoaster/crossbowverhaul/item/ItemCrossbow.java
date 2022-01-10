package de.dertoaster.crossbowverhaul.item;

import java.util.List;
import java.util.function.Predicate;

import de.dertoaster.crossbowverhaul.init.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.AbstractPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemCrossbow extends CrossbowItem implements IVanishable, IModifiedCrossbowMethod {

	protected static final Predicate<ItemStack> PREDICATE_BOLTS_ONLY = (itemstack) -> {
		return itemstack.getItem() instanceof ItemBolt;
	};
	
	protected static final Predicate<ItemStack> PREDICATE_BOLTS_ONLY_NO_EXPLOSIVE = (itemstack) -> {
		return itemstack.getItem() instanceof ItemBolt && !(itemstack.getItem() instanceof ItemBoltExplosive);
	};

	//This only checks the items in off and main hand
	@Override
	public Predicate<ItemStack> getSupportedHeldProjectiles() {
		return PREDICATE_BOLTS_ONLY_NO_EXPLOSIVE;
	}

	//Checks for all projectiles in the inventory
	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return PREDICATE_BOLTS_ONLY_NO_EXPLOSIVE;
	}

	public ItemCrossbow(Properties properties) {
		super(properties);
	}

	//DONE: Mixin interface to CrossbowItem that has methods for the below stuff
	//DONE: Add mixin / ASM that changes CrossbowItem.performShooting() to call a method from the item
	//DONE: Add mixin / ASM that changes CrossbowItem.getChargeDuration() to call a method from the item
	//DONE: Move CrossbowItem.shootProjectile() to Item
	
	@Override
	public void releaseUsing(ItemStack weaponItem, World world, LivingEntity shooter, int useDuration) {
		int i = this.getUseDuration(weaponItem) - useDuration;
		float f = CrossbowItem.getPowerForTime(i, weaponItem);
		if (f >= 1.0F && !CrossbowItem.isCharged(weaponItem) && this.tryLoadProjectiles(shooter, weaponItem)) {
			CrossbowItem.setCharged(weaponItem, true);
			SoundCategory soundcategory = shooter instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
			world.playSound((PlayerEntity) null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.CROSSBOW_LOADING_END, soundcategory, 1.0F, 1.0F / (random.nextFloat() * 0.5F + 1.0F) + 0.2F);
		}
	}

	// Attention: this actually differs from vanilla!
	protected boolean tryLoadProjectiles(LivingEntity shooter, ItemStack weaponItem) {
		int multishotEnchantLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MULTISHOT, weaponItem);
		int actualShotCount = 1 + (2 * multishotEnchantLevel);
		boolean flag = shooter instanceof PlayerEntity && ((PlayerEntity) shooter).abilities.instabuild;
		ItemStack itemstack = shooter.getProjectile(weaponItem);
		ItemStack itemStackForAdditionalProjectiles = itemstack.copy();

		for (int i = 0; i < actualShotCount; ++i) {
			if (i > 0) {
				itemstack = itemStackForAdditionalProjectiles.copy();
			}

			if (itemstack.isEmpty() && flag) {
				itemstack = new ItemStack(ModItems.ITEM_BOLT_IRON.get(), 1);
				itemStackForAdditionalProjectiles = itemstack.copy();
			}

			if (!this.loadProjectile(shooter, weaponItem, itemstack, i > 0, flag)) {
				return false;
			}
		}

		return true;
	}

	protected boolean loadProjectile(LivingEntity shooter, ItemStack weaponItem, ItemStack projectileItem, boolean forceCopyProjectileItem, boolean forceArrowSubTypeFlag) {
		//!!This is needed to avoid the arrows glitching in !!
		if(!(this.getAllSupportedProjectiles().test(projectileItem) || this.getSupportedHeldProjectiles().test(projectileItem))) {
			projectileItem = new ItemStack(ModItems.ITEM_BOLT_IRON.get(), 1);
		}
		if(shooter instanceof AbstractPiglinEntity) {
			projectileItem = new ItemStack(ModItems.ITEM_BOLT_GOLD.get(), 1);
		}
		if (projectileItem.isEmpty()) {
			return false;
		} else {
			boolean flag = forceArrowSubTypeFlag && projectileItem.getItem() instanceof ArrowItem;
			ItemStack itemstack;
			if (!flag && !forceArrowSubTypeFlag && !forceCopyProjectileItem) {
				itemstack = projectileItem.split(1);
				if (projectileItem.isEmpty() && shooter instanceof PlayerEntity) {
					((PlayerEntity) shooter).inventory.removeItem(projectileItem);
				}
			} else {
				itemstack = projectileItem.copy();
			}

			CrossbowItem.addChargedProjectile(weaponItem, itemstack);
			return true;
		}
	}
	
	public static int getBoltTier(ItemStack itemStack) {
		List<ItemStack> loadedProjectiles = getChargedProjectiles(itemStack);
		if(loadedProjectiles != null && !loadedProjectiles.isEmpty()) {
			for(ItemStack stack : loadedProjectiles) {
				if(stack.getItem() instanceof ItemBolt) {
					//System.out.println("Ordinal: " + ((ItemBolt)stack.getItem()).getTier().ordinal() + "   tier: " +  ((ItemBolt)stack.getItem()).getTier().toString());
					return getItemPropertyValueForTier(((ItemBolt) stack.getItem()).getTier());
				}
			}
		}
		return 0;
	}
	
	public static int getItemPropertyValueForTier(Tier tier) {
		for(Tiers etier : Tiers.values()) {
			if((Tier)etier == tier) {
				return etier.ordinal();
			}
		}
		return Tiers.values().length;
	}

	public static ItemStack getFirstLoadedBolt(ItemStack crossbow) {
		List<ItemStack> loadedProjectiles = getChargedProjectiles(crossbow);
		if(loadedProjectiles != null && !loadedProjectiles.isEmpty()) {
			for(ItemStack stack : loadedProjectiles) {
				if(stack.getItem() instanceof ItemBolt) {
					return stack;
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	//Override charging duration
	@Override
	public int getMaxChargeTime() {
		return 2 * IModifiedCrossbowMethod.super.getMaxChargeTime();
	}
	
}
