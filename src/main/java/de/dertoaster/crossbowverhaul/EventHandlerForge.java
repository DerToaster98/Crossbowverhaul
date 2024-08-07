package de.dertoaster.crossbowverhaul;

import de.dertoaster.crossbowverhaul.mixin.accessor.AccessorLootTable;
import de.dertoaster.crossbowverhaul.mixin.accessor.AccessorLootTableBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;

@EventBusSubscriber(modid = CrossbowverhaulMod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class EventHandlerForge {
	
	@SubscribeEvent
	public static void onLootTableLoad(LootTableLoadEvent event) {
		LootTable lt = event.getTable();
		//First: Check if this is the piglin or illager loottable
		if(event.getName().equals(EntityType.PILLAGER.getDefaultLootTable())) {
			LootTable.Builder builder = LootTable.lootTable()
				.withPool(LootPool.lootPool()
					//.name("crossbowverhaul")
					.add(
						LootTableReference.lootTableReference(
							new ResourceLocation(CrossbowverhaulMod.MODID, "lootmodifiers/pillager")
						)
					)
				); 
			for(LootPool pool : ((AccessorLootTable)lt).getPools()) {
				((AccessorLootTableBuilder)builder).getPools().add(pool);
			}
			event.setTable(
				builder.build()
			);
		}
		else if(event.getName().equals(EntityType.PIGLIN.getDefaultLootTable()) || event.getName().equals(EntityType.ZOMBIFIED_PIGLIN.getDefaultLootTable())) {
			LootTable.Builder builder = LootTable.lootTable()
				.withPool(LootPool.lootPool()
					//.name("crossbowverhaul")
					.add(
						LootTableReference.lootTableReference(
							new ResourceLocation(CrossbowverhaulMod.MODID, "lootmodifiers/piglin")
						)
					)
				); 
			for(LootPool pool : lt.getPools()) {
				((AccessorLootTableBuilder)builder).getPools().add(pool);
			}
			event.setTable(
				builder.build()
			);
		} 
	}
	
}
