package de.dertoaster.crossbowverhaul.client.init;

import de.dertoaster.crossbowverhaul.CrossbowverhaulMod;
import de.dertoaster.crossbowverhaul.client.renderer.entity.RenderProjectileBolt;
import de.dertoaster.crossbowverhaul.init.ModEntityTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = CrossbowverhaulMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		EntityRenderers.register(ModEntityTypes.BOLT.get(), RenderProjectileBolt::new);
		EntityRenderers.register(ModEntityTypes.BOLT_EXPLOSIVE.get(), RenderProjectileBolt::new);
	}
}
