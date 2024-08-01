package de.dertoaster.crossbowverhaul;

import de.dertoaster.crossbowverhaul.config.COConfig;
import de.dertoaster.crossbowverhaul.init.ModEntityTypes;
import de.dertoaster.crossbowverhaul.init.ModItemProperties;
import de.dertoaster.crossbowverhaul.init.ModItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CrossbowverhaulMod.MODID)
@EventBusSubscriber(modid = CrossbowverhaulMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CrossbowverhaulMod
{
	public static final String MODID = "crossbowverhaul";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public CrossbowverhaulMod(IEventBus bus, Dist dist) {
    	//Register config
    	ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, COConfig.CONFIG_SPEC, "co-config.toml");
    	COConfig.loadConfig(COConfig.CONFIG_SPEC,
				FMLPaths.CONFIGDIR.get().resolve("co-config.toml").toString());
    	
    	//Register items
    	ModItems.registerToEventBus(bus);
    	//Register entities
    	ModEntityTypes.ENTITY_TYPES.register(bus);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    	event.enqueueWork(() -> ModItemProperties.register());
    }

}
