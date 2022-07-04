package de.dertoaster.crossbowverhaul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.dertoaster.crossbowverhaul.config.CrossbowverhaulConfigHolder;
import de.dertoaster.crossbowverhaul.init.ModEnchantments;
import de.dertoaster.crossbowverhaul.init.ModEntityTypes;
import de.dertoaster.crossbowverhaul.init.ModItemProperties;
import de.dertoaster.crossbowverhaul.init.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CrossbowverhaulMod.MODID)
@EventBusSubscriber(modid = CrossbowverhaulMod.MODID, bus = Bus.MOD)
public class CrossbowverhaulMod
{
	public static final String MODID = "crossbowverhaul";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public CrossbowverhaulMod() {
    	IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	//Register config
    	ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CrossbowverhaulConfigHolder.CONFIG_SPEC, "co-config.toml");
    	
    	//Register items
    	ModItems.registerToEventBus(modbus);
    	//Register entities
    	ModEntityTypes.ENTITY_TYPES.register(modbus);
    	//Register enchantment overrides
    	ModEnchantments.registerToEventBus(modbus);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    	event.enqueueWork(() -> ModItemProperties.register());
    }

}
