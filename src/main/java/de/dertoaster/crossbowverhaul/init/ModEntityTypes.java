package de.dertoaster.crossbowverhaul.init;

import de.dertoaster.crossbowverhaul.CrossbowverhaulMod;
import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBolt;
import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBoltExplosive;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, CrossbowverhaulMod.MODID);

	public static final RegistryObject<EntityType<ProjectileBolt>> BOLT = ENTITY_TYPES.register("bolt", () -> EntityType.Builder.<ProjectileBolt>of(ProjectileBolt::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4)
			.updateInterval(20).build(new ResourceLocation(CrossbowverhaulMod.MODID, "bolt").toString()));
	
	public static final RegistryObject<EntityType<ProjectileBoltExplosive>> BOLT_EXPLOSIVE = ENTITY_TYPES.register("bolt_explosive", () -> EntityType.Builder.<ProjectileBoltExplosive>of(ProjectileBoltExplosive::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4)
			.updateInterval(20).build(new ResourceLocation(CrossbowverhaulMod.MODID, "bolt_explosive").toString()));

}
