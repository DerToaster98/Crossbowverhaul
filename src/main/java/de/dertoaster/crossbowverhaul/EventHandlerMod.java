package de.dertoaster.crossbowverhaul;

import de.dertoaster.crossbowverhaul.init.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = CrossbowverhaulMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EventHandlerMod {
	
	@SubscribeEvent
	public static void assignItemsToTabs(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey().equals(CreativeModeTabs.COMBAT)) {
			event.accept(ModItems.ITEM_BOLT_IRON.get());
			event.accept(ModItems.ITEM_BOLT_GOLD.get());
			event.accept(ModItems.ITEM_BOLT_DIAMOND.get());
			event.accept(ModItems.ITEM_BOLT_NETHERITE.get());
			
			event.accept(ModItems.ITEM_BOLT_EXPLOSIVE.get());
			
			event.accept(ModItems.ITEM_CROSSBOW_NETHERITE);
		}
	}
	
}
