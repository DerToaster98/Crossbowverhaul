package de.dertoaster.crossbowverhaul.item;

import java.util.List;
import java.util.function.Predicate;

import de.dertoaster.crossbowverhaul.config.COConfig;
import de.dertoaster.crossbowverhaul.init.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ItemCrossbow extends CrossbowItem implements Vanishable, IModifiedCrossbowMethod {

	protected static final Predicate<ItemStack> PREDICATE_BOLTS_ONLY = (itemstack) -> {
		return itemstack.getItem() instanceof ItemBolt;
	};
	
	protected static final Predicate<ItemStack> PREDICATE_BOLTS_ONLY_NO_EXPLOSIVE = (itemstack) -> {
		return itemstack.getItem() instanceof ItemBolt && (COConfig.CONFIG.coAllowExplosiveBoltsOnNormalCrossbow.get().booleanValue() || !(itemstack.getItem() instanceof ItemBoltExplosive));
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
	public void releaseUsing(ItemStack weaponItem, Level world, LivingEntity shooter, int useDuration) {
		int i = this.getUseDuration(weaponItem) - useDuration;
		float f = CrossbowItem.getPowerForTime(i, weaponItem);
		if (f >= 1.0F && !CrossbowItem.isCharged(weaponItem) && this.tryLoadProjectiles(shooter, weaponItem)) {
			CrossbowItem.tryLoadProjectiles(weaponItem, true);
			SoundSource soundcategory = shooter instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
			world.playSound((Player) null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.CROSSBOW_LOADING_END, soundcategory, 1.0F, 1.0F / (shooter.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
		}
	}

	// Attention: this actually differs from vanilla!
	protected boolean tryLoadProjectiles(LivingEntity shooter, ItemStack weaponItem) {
		int multishotEnchantLevel = weaponItem.getEnchantmentLevel(Enchantments.MULTISHOT);
		int actualShotCount = 1 + (2 * multishotEnchantLevel);
		boolean flag = shooter instanceof Player && (((Player) shooter).getAbilities().instabuild || weaponItem.getEnchantmentLevel(Enchantments.INFINITY_ARROWS) > 0);
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
		if(shooter instanceof AbstractPiglin) {
			projectileItem = new ItemStack(ModItems.ITEM_BOLT_GOLD.get(), 1);
		}
		if (projectileItem.isEmpty()) {
			return false;
		} else {
			boolean flag = forceArrowSubTypeFlag && projectileItem.getItem() instanceof ArrowItem;
			ItemStack itemstack;
			if (!flag && !forceArrowSubTypeFlag && !forceCopyProjectileItem) {
				itemstack = projectileItem.split(1);
				if (projectileItem.isEmpty() && (shooter instanceof Player)) {
					((Player) shooter).getInventory().removeItem(projectileItem);
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
				if(stack.getItem() instanceof AbstractItemBolt) {
					return ((AbstractItemBolt) stack.getItem()).getIdForItemProperties();
				}
			}
		}
		return 0;
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
		return COConfig.CONFIG.coModCrossbowChargeTime.get().intValue();
	}
	
	@Override
	public int getDefaultProjectileRange() {
		return (int)(COConfig.CONFIG.coModCrossbowProjectileRange.get().doubleValue() * super.getDefaultProjectileRange());
	}
	
	@Override
	public float getProjectileSpeedModifier() {
		float f = COConfig.CONFIG.coModCrossbowProjectileSpeed.get().floatValue(); 
		return f * IModifiedCrossbowMethod.super.getProjectileSpeedModifier();
	}
	
	protected boolean parentClassIsEnchantable(ItemStack stack) {
		return super.isEnchantable(stack);
	}
	
	protected boolean parentClassIsBookEnchantable(ItemStack stack, ItemStack book) {
		return super.isEnchantable(stack);
	}
	
	@Override
	public boolean isEnchantable(ItemStack p_41456_) {
		return this.parentClassIsEnchantable(p_41456_) && COConfig.CONFIG.coEnchCrossbow.get().booleanValue();
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return this.parentClassIsBookEnchantable(stack, book) && COConfig.CONFIG.coEnchCrossbow.get().booleanValue();
	}

	@Override
	public ItemStack getDefaultCreativeAmmo(@Nullable Player player, ItemStack projectileWeaponItem) {
		return ModItems.ITEM_BOLT_IRON.get().getDefaultInstance();
	}
}
