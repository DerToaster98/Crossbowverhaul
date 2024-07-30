package de.dertoaster.crossbowverhaul.init;

import de.dertoaster.crossbowverhaul.CrossbowverhaulMod;
import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBolt;
import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBoltExplosive;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, CrossbowverhaulMod.MODID);

	public static final DeferredHolder<EntityType<?>, EntityType<ProjectileBolt>> BOLT = ENTITY_TYPES.register("bolt", () -> EntityType.Builder.<ProjectileBolt>of(ProjectileBolt::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4)
			.updateInterval(20).build(ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "bolt").toString()));
	
	public static final DeferredHolder<EntityType<?>, EntityType<ProjectileBoltExplosive>> BOLT_EXPLOSIVE = ENTITY_TYPES.register("bolt_explosive", () -> EntityType.Builder.<ProjectileBoltExplosive>of(ProjectileBoltExplosive::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4)
			.updateInterval(20).build(ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "bolt_explosive").toString()));

}
