package de.dertoaster.crossbowverhaul.init;

import de.dertoaster.crossbowverhaul.enchantment.MultishotEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEnchantments {
	
	public static void registerToEventBus(IEventBus eventbus) {
		VANILLA_ENCHANTMENTS.register(eventbus);
	}
	
	static final DeferredRegister<Enchantment> VANILLA_ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, "minecraft");

	public static final RegistryObject<Enchantment> ENCHANTMENT_MULTISHOT = VANILLA_ENCHANTMENTS.register("multishot", () -> {
		return CrossbowverhaulConfigHolder.coModMultishot.get() ? new MultishotEnchantment(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND) : Enchantments.MULTISHOT;
	});
	
}
