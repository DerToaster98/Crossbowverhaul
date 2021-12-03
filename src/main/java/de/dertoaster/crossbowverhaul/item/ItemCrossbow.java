package de.dertoaster.crossbowverhaul.item;

import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.Lists;

import de.dertoaster.crossbowverhaul.init.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemCrossbow extends CrossbowItem implements IVanishable {

	protected static final Predicate<ItemStack> PREDICATE_BOLTS = (itemstack) -> {
		return itemstack.getItem() instanceof ItemBolt;
	};

	@Override
	public Predicate<ItemStack> getSupportedHeldProjectiles() {
		return PREDICATE_BOLTS;
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return PREDICATE_BOLTS;
	}

	public ItemCrossbow(Properties properties) {
		super(properties);
	}

	@Override
	public int getUseDuration(ItemStack item) {
		// As wished by brass: 50% SLOWER loading time => x2
		return 2 * super.getUseDuration(item);
	}

	// Copied from vanilla cause for fucks sake methods are static -___-

	@Override
	public void releaseUsing(ItemStack weaponItem, World world, LivingEntity shooter, int useDuration) {
		int i = this.getUseDuration(weaponItem) - useDuration;
		float f = this.getPowerForTime(i, weaponItem);
		if (f >= 1.0F && !CrossbowItem.isCharged(weaponItem) && this.tryLoadProjectiles(shooter, weaponItem)) {
			CrossbowItem.setCharged(weaponItem, true);
			SoundCategory soundcategory = shooter instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
			world.playSound((PlayerEntity) null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.CROSSBOW_LOADING_END, soundcategory, 1.0F, 1.0F / (random.nextFloat() * 0.5F + 1.0F) + 0.2F);
		}
	}

	// More vanilla copies...
	protected float getPowerForTime(int useDuration, ItemStack weaponItem) {
		float f = (float) useDuration / (float) getChargeDuration(weaponItem);
		if (f > 1.0F) {
			f = 1.0F;
		}

		return f;
	}

	protected void addChargedProjectile(ItemStack weaponItem, ItemStack projectileItem) {
		CompoundNBT compoundnbt = weaponItem.getOrCreateTag();
		ListNBT listnbt;
		if (compoundnbt.contains("ChargedProjectiles", 9)) {
			listnbt = compoundnbt.getList("ChargedProjectiles", 10);
		} else {
			listnbt = new ListNBT();
		}

		CompoundNBT compoundnbt1 = new CompoundNBT();
		projectileItem.save(compoundnbt1);
		listnbt.add(compoundnbt1);
		compoundnbt.put("ChargedProjectiles", listnbt);
	}

	// Attention: this actually differs from vanilla!
	protected boolean tryLoadProjectiles(LivingEntity shooter, ItemStack weaponItem) {
		int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MULTISHOT, weaponItem);
		int j = i == 0 ? 1 : 3;
		boolean flag = shooter instanceof PlayerEntity && ((PlayerEntity) shooter).abilities.instabuild;
		ItemStack itemstack = shooter.getProjectile(weaponItem);
		ItemStack itemstack1 = itemstack.copy();

		for (int k = 0; k < j; ++k) {
			if (k > 0) {
				itemstack = itemstack1.copy();
			}

			if (itemstack.isEmpty() && flag) {
				itemstack = new ItemStack(ModItems.ITEM_BOLT_IRON.get(), 1);
				itemstack1 = itemstack.copy();
			}

			if (!loadProjectile(shooter, weaponItem, itemstack, k > 0, flag)) {
				return false;
			}
		}

		return true;
	}

	protected boolean loadProjectile(LivingEntity shooter, ItemStack weaponItem, ItemStack projectileItem, boolean forceCopyProjectileItem, boolean forceArrowSubTypeFlag) {
		//!!This is needed to avoid the arrows glitching in !!
		if(!PREDICATE_BOLTS.test(projectileItem)) {
			projectileItem = new ItemStack(ModItems.ITEM_BOLT_IRON.get(), 1);
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

			addChargedProjectile(weaponItem, itemstack);
			return true;
		}
	}
	
	//Copy from vanilla
	public static List<ItemStack> getChargedProjectiles(ItemStack p_220018_0_) {
	      List<ItemStack> list = Lists.newArrayList();
	      CompoundNBT compoundnbt = p_220018_0_.getTag();
	      if (compoundnbt != null && compoundnbt.contains("ChargedProjectiles", 9)) {
	         ListNBT listnbt = compoundnbt.getList("ChargedProjectiles", 10);
	         if (listnbt != null) {
	            for(int i = 0; i < listnbt.size(); ++i) {
	               CompoundNBT compoundnbt1 = listnbt.getCompound(i);
	               list.add(ItemStack.of(compoundnbt1));
	            }
	         }
	      }

	      return list;
	   }

	public static int getBoltTier(ItemStack itemStack) {
		List<ItemStack> loadedProjectiles = getChargedProjectiles(itemStack);
		if(loadedProjectiles != null && !loadedProjectiles.isEmpty()) {
			for(ItemStack stack : loadedProjectiles) {
				if(stack.getItem() instanceof ItemBolt) {
					//System.out.println("Ordinal: " + ((ItemBolt)stack.getItem()).getTier().ordinal() + "   tier: " +  ((ItemBolt)stack.getItem()).getTier().toString());
					return ((ItemBolt)stack.getItem()).getTier().ordinal();
				}
			}
		}
		return 0;
	}

}
