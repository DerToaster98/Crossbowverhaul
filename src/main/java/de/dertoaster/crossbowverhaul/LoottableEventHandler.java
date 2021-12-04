package de.dertoaster.crossbowverhaul;

import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool.Builder;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
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
			lt.addPool(new Builder()
					.name("crossbowverhaul")
					.add(
							TableLootEntry.lootTableReference(
								new ResourceLocation(CrossbowverhaulMod.MODID, "lootmodifiers/pillager")
							)
						)
					.build()
			);
		}
		else if(event.getName().equals(EntityType.PIGLIN.getDefaultLootTable()) || event.getName().equals(EntityType.ZOMBIFIED_PIGLIN.getDefaultLootTable())) {
			lt.addPool(new Builder()
					.name("crossbowverhaul")
					.add(
							TableLootEntry.lootTableReference(
								new ResourceLocation(CrossbowverhaulMod.MODID, "lootmodifiers/piglin")
							)
						)
					.build()
			);
		}
	}
	
}
