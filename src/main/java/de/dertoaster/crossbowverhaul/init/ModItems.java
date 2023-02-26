package de.dertoaster.crossbowverhaul.init;

import de.dertoaster.crossbowverhaul.CrossbowverhaulMod;
import de.dertoaster.crossbowverhaul.config.CrossbowverhaulConfigHolder;
import de.dertoaster.crossbowverhaul.item.ItemBolt;
import de.dertoaster.crossbowverhaul.item.ItemBoltExplosive;
import de.dertoaster.crossbowverhaul.item.ItemCrossbow;
import de.dertoaster.crossbowverhaul.item.ItemCrossbowNetherite;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	
	public static void registerToEventBus(IEventBus eventbus) {
		ITEMS.register(eventbus);
		VANILLA_ITEMS.register(eventbus);
	}

	static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CrossbowverhaulMod.MODID);
	static final DeferredRegister<Item> VANILLA_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");
	

	public static final RegistryObject<Item> ITEM_BOLT_IRON = ITEMS.register("bolt_iron", () -> new ItemBolt((new Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(64)), ItemTier.IRON));
	public static final RegistryObject<Item> ITEM_BOLT_GOLD = ITEMS.register("bolt_gold", () -> new ItemBolt((new Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(64)), ItemTier.GOLD));
	public static final RegistryObject<Item> ITEM_BOLT_DIAMOND = ITEMS.register("bolt_diamond", () -> new ItemBolt((new Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(64)), ItemTier.DIAMOND));
	public static final RegistryObject<Item> ITEM_BOLT_NETHERITE = ITEMS.register("bolt_netherite", () -> new ItemBolt((new Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(64).fireResistant()), ItemTier.NETHERITE));
	
	//Explosive bolt
	public static final RegistryObject<Item> ITEM_BOLT_EXPLOSIVE = ITEMS.register("bolt_explosive", () -> new ItemBoltExplosive(new Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(64).fireResistant()));

	//Netherite crossbow
	public static final RegistryObject<Item> ITEM_CROSSBOW_NETHERITE = ITEMS.register("crossbow_netherite", () -> (new ItemCrossbowNetherite(new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_COMBAT).durability(CrossbowverhaulConfigHolder.coNetheriteCrossbowDurability.get()).fireResistant())));
	
	//now, override the crossbow
	public static final RegistryObject<Item> ITEM_CROSSBOW = VANILLA_ITEMS.register("crossbow", () -> (new ItemCrossbow(new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_COMBAT).durability(CrossbowverhaulConfigHolder.coCrossbowDurability.get()))));

}
