package de.dertoaster.crossbowverhaul.init;

import de.dertoaster.crossbowverhaul.enchantment.MultishotEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
	
	public static void registerToEventBus(IEventBus eventbus) {
		VANILLA_ENCHANTMENTS.register(eventbus);
	}
	
	static final DeferredRegister<Enchantment> VANILLA_ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, "minecraft");

	public static final RegistryObject<Enchantment> ENCHANTMENT_MULTISHOT = VANILLA_ENCHANTMENTS.register("multishot", () -> new MultishotEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
	
}
