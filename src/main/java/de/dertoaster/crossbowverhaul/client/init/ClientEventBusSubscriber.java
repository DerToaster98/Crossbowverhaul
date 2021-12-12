package de.dertoaster.crossbowverhaul.client.init;

import de.dertoaster.crossbowverhaul.CrossbowverhaulMod;
import de.dertoaster.crossbowverhaul.client.renderer.entity.RenderProjectileBolt;
import de.dertoaster.crossbowverhaul.init.ModEntityTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CrossbowverhaulMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		EntityRenderers.register(ModEntityTypes.BOLT.get(), RenderProjectileBolt::new);
		EntityRenderers.register(ModEntityTypes.BOLT_EXPLOSIVE.get(), RenderProjectileBolt::new);
	}
}
