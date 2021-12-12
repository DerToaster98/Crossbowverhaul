package de.dertoaster.crossbowverhaul;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = CrossbowverhaulMod.MODID, bus = Bus.FORGE)
public class LoottableEventHandler {
	
	@SubscribeEvent
	public static void onLootTableLoad(LootTableLoadEvent event) {
		LootTable lt = event.getTable();
		//First: Check if this is the piglin or illager loottable
		if(event.getName().equals(EntityType.PILLAGER.getDefaultLootTable())) {
			lt.addPool(new LootPool.Builder()
					.name("crossbowverhaul")
					.add(
							LootTableReference.lootTableReference(
								new ResourceLocation(CrossbowverhaulMod.MODID, "lootmodifiers/pillager")
							)
						)
					.build()
			);
		}
		else if(event.getName().equals(EntityType.PIGLIN.getDefaultLootTable()) || event.getName().equals(EntityType.ZOMBIFIED_PIGLIN.getDefaultLootTable())) {
			lt.addPool(new LootPool.Builder()
					.name("crossbowverhaul")
					.add(
							LootTableReference.lootTableReference(
								new ResourceLocation(CrossbowverhaulMod.MODID, "lootmodifiers/piglin")
							)
						)
					.build()
			);
		}
	}
	
}
