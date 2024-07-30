package de.dertoaster.crossbowverhaul.init;

import de.dertoaster.crossbowverhaul.CrossbowverhaulMod;
import de.dertoaster.crossbowverhaul.config.COConfig;
import de.dertoaster.crossbowverhaul.item.ItemBolt;
import de.dertoaster.crossbowverhaul.item.ItemBoltExplosive;
import de.dertoaster.crossbowverhaul.item.ItemCrossbow;
import de.dertoaster.crossbowverhaul.item.ItemCrossbowNetherite;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModItems {

    public static void registerToEventBus(IEventBus eventbus) {
        ITEMS.register(eventbus);
        VANILLA_ITEMS.register(eventbus);
    }

    static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CrossbowverhaulMod.MODID);
    static final DeferredRegister.Items VANILLA_ITEMS = DeferredRegister.createItems("minecraft");


    public static final DeferredItem<Item> ITEM_BOLT_IRON = ITEMS.register("bolt_iron", () -> {
        return new ItemBolt(new Item.Properties().stacksTo(64), Tiers.IRON);
    });
    public static final DeferredItem<Item> ITEM_BOLT_GOLD = ITEMS.register("bolt_gold", () -> new ItemBolt(new Item.Properties().stacksTo(64), Tiers.GOLD));
    public static final DeferredItem<Item> ITEM_BOLT_DIAMOND = ITEMS.register("bolt_diamond", () -> new ItemBolt(new Item.Properties().stacksTo(64), Tiers.DIAMOND));
    public static final DeferredItem<Item> ITEM_BOLT_NETHERITE = ITEMS.register("bolt_netherite", () -> new ItemBolt(new Item.Properties().stacksTo(64).fireResistant(), Tiers.NETHERITE));

    //Explosive bolt
    public static final DeferredItem<Item> ITEM_BOLT_EXPLOSIVE = ITEMS.register("bolt_explosive", () -> new ItemBoltExplosive(new Item.Properties().stacksTo(64).fireResistant()));

    //Netherite crossbow
    public static final DeferredItem<Item> ITEM_CROSSBOW_NETHERITE = ITEMS.register("crossbow_netherite", () -> (new ItemCrossbowNetherite(new Item.Properties().stacksTo(1).durability(COConfig.CONFIG.coNetheriteCrossbowDurability.get()).fireResistant())));

    //now, override the crossbow
    public static final DeferredItem<Item> ITEM_CROSSBOW = VANILLA_ITEMS.register("crossbow", () -> (new ItemCrossbow(new Item.Properties().stacksTo(1).durability(COConfig.CONFIG.coCrossbowDurability.get()))));

}
